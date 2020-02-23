package com.qiyuesuo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qiyuesuo.entity.File;
import com.qiyuesuo.utils.JDBCUtil;

/*
 *Administrator
 *2020年2月21日
 *   FileDao实现类
*/
public class FileDaoImp implements FileDao {

	private Logger logger = Logger.getLogger(FileDaoImp.class);

	// 数据库路径
	private final static String DB_URL = "jdbc:derby:E:\\DeverlopmentTool\\derby\\database\\firstdb;";
	// Driver
	private final static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	// 增加上传文件
	@Override
	public int addFile(File file) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		int result = 0;
		try {
			Class.forName(DRIVER).newInstance(); // 加载数据库驱动
			conn = DriverManager.getConnection(DB_URL, "root", "root");

			String sql = "insert into file(f_size,f_type,f_oldname,f_savepath,f_date,f_key) values(?,?,?,?,?,?)";
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, file.getF_size());
			pStatement.setString(2, file.getF_type());
			pStatement.setString(3, file.getF_oldName());
			pStatement.setString(4, file.getF_savePath());
			pStatement.setString(5, file.getF_date());
			pStatement.setString(6, file.getF_key());

			result = pStatement.executeUpdate();
			logger.info("执行sql完毕...................");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	//查询下载
	public List<File> getAllFile() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		List<File> files = new ArrayList<File>();

		try {
			conn = JDBCUtil.getConnection();
			// sql语句
			String sql = "select * from file";
			// 预编译sql语句
			pStatement = JDBCUtil.getPreparedStatement(sql, conn);
			rSet = JDBCUtil.getResultSet(pStatement);

			while (rSet.next()) {
                files.add(new File(rSet.getInt(1), rSet.getInt(2), rSet.getString(3), rSet.getString(4), rSet.getString(6), rSet.getString(8),rSet.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, pStatement, rSet);
		}
		if(files.size()>0){
			logger.info("file查询成功.......................................................................");
		}
		return files;
	}

	//获取指定文件，用于文件的下载，
	@Override
	public File getFile(int f_id) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		File file = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select * from file where f_id=?";
			pStatement = JDBCUtil.getPreparedStatement(sql, connection);
			pStatement.setInt(1, f_id);
			
			rSet = pStatement.executeQuery();
			rSet.next();
			file = new File(rSet.getInt(1), rSet.getInt(2), rSet.getString(3), rSet.getString(4), rSet.getString(6), rSet.getString(8),rSet.getString(9));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(connection, pStatement, rSet);
		}
		if(file!=null){
			logger.info("查询下载文件元数据成功!。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
			System.out.println("加密后的对称密匙key值为："+file.getF_key());
		}
		
		return file;
	}

}
