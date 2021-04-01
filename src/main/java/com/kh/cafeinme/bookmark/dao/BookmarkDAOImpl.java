package com.kh.cafeinme.bookmark.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.cafeinme.mycafe.vo.CafeVO;


@Repository
@Transactional
public class BookmarkDAOImpl implements BookmarkDAO{
@Autowired
JdbcTemplate jt;

	/**
	 * 회원 북마크 조회
	 * @param cafe_no
	 * @param member_id
	 * @return
	 */
	@Override
	public int bookmark(int cafe_no, String member_id) {
		String SQL = "insert into bookmarks values(?,?)";
		
		return jt.update(SQL,member_id,cafe_no);
	}

	/**
	 * 북마크 삭제
	 * @param cafe_no
	 * @param member_id
	 * @return
	 */
	@Override
	public int delbookmark(int cafe_no, String member_id) {
		String SQL = "delete bookmarks where cafe_no = ? and member_id = ?";
		return jt.update(SQL,cafe_no,member_id);
	}

	/**
	 * 회원 북마크 리스트 확인
	 * @param member_id
	 * @return
	 */
	@Override
	public List<CafeVO> mybookmark(String member_id) {
		List<CafeVO> list = new ArrayList<CafeVO>();
		if(ishavebookmark(member_id)) {
			StringBuilder SQL = new StringBuilder();
			SQL.append("select t1.cafe_no,t1.cafe_name,t2.cfile_path,to_char(t1.cafe_content) as cafe_content ");
			SQL.append("from cafe t1 LEFT OUTER join cafefile t2 on t2.cafe_no = t1.cafe_no ");
			SQL.append("group by t1.cafe_no,t1.cafe_name,t2.cfile_path,to_char(t1.cafe_content) ");
			SQL.append("having (cfile_path in ");
			SQL.append("(select cfile_path from (select ROW_number() OVER(partition By cafe_no Order by cafe_no,cfile_path) Rn ,cafe_no,cfile_path from cafefile) ");
			SQL.append("where Rn =1) or cfile_path is NULL) ");
			SQL.append("and t1.cafe_no in (select cafe_no from bookmarks where member_id =?)");
			list = jt.query(SQL.toString(), new BeanPropertyRowMapper<CafeVO>(CafeVO.class),member_id);
		}
		return list;
	}
	
	/**
	 * 북마크 존재 여부 확인
	 * @param member_id
	 * @return
	 */
	private boolean ishavebookmark(String member_id) {
		boolean result = false;
		String SQL = "select count(*) from bookmarks where member_id = ?";
		int r = jt.queryForObject(SQL, Integer.class,member_id);
		if(r!=0) {
			result = true;
		}
		return result;
	}
}
