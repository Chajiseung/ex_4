package com.choa.free;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.util.DBConnect;
import com.choa.util.Rowmaker;

@Repository
public class FreeBoardDAOImpl implements BoardDAO {
	
	@Inject
	private DataSource dataSource;
	
	public void test(){
		
		System.out.println(dataSource);
	}

	@Override
	public List<BoardDTO> boardList(Rowmaker rowmaker) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs =null;
		FreeBoradDTO freeBoradDTO = null;
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		
		String sql ="select * from"
				+ " (select rownum r, n.* from"
				+ " (select * from qna order by ref desc step asc) n)"
				+ " where r between ? and ?";
		
			st = con.prepareStatement(sql);
			
			st.setInt(1, rowmaker.getStartRow());
			st.setInt(2, rowmaker.getLastRow());
			rs = st.executeQuery();
			while(rs.next()){
				
				freeBoradDTO = new FreeBoradDTO();
				freeBoradDTO.setNum(rs.getInt("num"));
				freeBoradDTO.setWriter(rs.getString("writer"));
				freeBoradDTO.setTitle(rs.getString("title"));
				freeBoradDTO.setContents(rs.getString("contents"));
				freeBoradDTO.setReg_date(rs.getDate("reg_date"));
				freeBoradDTO.setHit(rs.getInt("hit"));
				freeBoradDTO.setRef(rs.getInt("ref"));
				freeBoradDTO.setStep(rs.getInt("step"));
				freeBoradDTO.setDepth(rs.getInt("depth"));
				ar.add(freeBoradDTO);
			}
		DBConnect.disConnect(rs, st, con);
			
		
		return ar;
	}

	@Override
	public FreeBoradDTO boardView(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs =null;
		FreeBoradDTO freeBoradDTO = null;
		
		String sql ="select * from qna where num=?";
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		rs = st.executeQuery();
		if(rs.next()){
			
			freeBoradDTO = new FreeBoradDTO();
			freeBoradDTO.setNum(rs.getInt("num"));
			freeBoradDTO.setWriter(rs.getString("writer"));
			freeBoradDTO.setTitle(rs.getString("title"));
			freeBoradDTO.setContents(rs.getString("contents"));
			freeBoradDTO.setReg_date(rs.getDate("reg_date"));
			freeBoradDTO.setHit(rs.getInt("hit"));
			freeBoradDTO.setRef(rs.getInt("ref"));
			freeBoradDTO.setStep(rs.getInt("step"));
			freeBoradDTO.setDepth(rs.getInt("depth"));
		}
		
		DBConnect.disConnect(st, con);
		
		return freeBoradDTO;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql ="insert into qna values(point_seq.nextval, ?, ?, ?, sysdate, 0, point_seq.currval, 0, 0)";
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
		
		String sql ="update qna set title=?, contents=? where num=?";
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
		
		String sql ="delete qna where num=?";
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
		
		String sql ="select nvl(count(num), 0) from qna";
		st= con.prepareStatement(sql);
		
		rs =st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		
		DBConnect.disConnect(rs, st, con);
		
		return result;
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
