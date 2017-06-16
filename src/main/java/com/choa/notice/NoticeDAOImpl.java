package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.DBConnect;
import com.choa.util.Rowmaker;

@Repository
// NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAOImpl implements BoardDAO{
	
	@Inject
	// ¡÷¿‘
	private DataSource dataSource;
	
	
	
	
	
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/
	
	@Override
	public List<BoardDTO> boardList(Rowmaker rowmaker) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs =null;
		NoticeDTO noticeDTO = null;
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		
		String sql ="select * from"
				+ " (select rownum r, n.* from"
				+ " (select * from notice order by num desc) n)"
				+ " where r between ? and ?";
		
			st = con.prepareStatement(sql);
			
			st.setInt(1, rowmaker.getStartRow());
			st.setInt(2, rowmaker.getLastRow());
			rs = st.executeQuery();
			while(rs.next()){
				
				noticeDTO = new NoticeDTO();
				noticeDTO.setNum(rs.getInt("num"));
				noticeDTO.setWriter(rs.getString("writer"));
				noticeDTO.setTitle(rs.getString("title"));
				noticeDTO.setContents(rs.getString("contents"));
				noticeDTO.setReg_date(rs.getDate("reg_date"));
				noticeDTO.setHit(rs.getInt("hit"));
				ar.add(noticeDTO);
			}
		DBConnect.disConnect(rs, st, con);
			
		
		return ar;
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs =null;
		NoticeDTO noticeDTO = null;
		
		String sql ="select * from notice where num=?";
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		rs = st.executeQuery();
		if(rs.next()){
			
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}
		
		DBConnect.disConnect(st, con);
		
		return noticeDTO;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql ="insert into notice values(point_seq.nextval, ?, ?, ?, sysdate, 0)";
		st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		
		
		return result;
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql ="update notice set title=?, contents=? where num=?";
		st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
		
	}

	@Override
	public int boradDelete(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql ="delete notice where num=?";
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		
		return result;
	}

	@Override
	public int boardCount() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs =null;
		
		String sql ="select nvl(count(num), 0) from notice";
		st= con.prepareStatement(sql);
		
		rs =st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		
		DBConnect.disConnect(rs, st, con);
		
		return result;
	}

	@Override
	public void boardHit(int num) throws Exception {
		
		
	}

	/*public int noticeCount() throws Exception{
		
		

	public List<NoticeDTO> noticeList(Rowmaker rowmaker) throws Exception{
		
		
	
	public NoticeDTO noticeView(int num) throws Exception{
		
		
	
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		
	
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception{
		
	
	public int noticeDelete(int num) throws Exception{
		*/

}
