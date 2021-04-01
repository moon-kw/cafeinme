package com.kh.cafeinme.search.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.menu.vo.MenuVO;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.mycafe.vo.CfileVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class SearchDAOImpl implements SearchDAO{

 private final JdbcTemplate jt;
 
	/**
	 * 위치로 카페 검색
	 * @param keyword
	 * @return
	 */
	@Override
	public List<CafeVO> searchroad(String keyword) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select t1.cafe_no,t1.cafe_name,t1.cafe_address1");
		SQL.append(",t1.cafe_address2,t1.cafe_tel,t1.open_time,t1.close_time,t4.cfile_path,");
		SQL.append("LiSTAGG(tag_name,',') Within group(order by t1.cafe_no) as tagnames ");
		SQL.append("from cafe t1 inner join cafetags t2 on t2.cafe_no = t1.cafe_no ");
		SQL.append("inner join tags t3 on t3.tag_no = t2.tag_no ");
		SQL.append("LEFT OUTER join cafefile t4 on t4.cafe_no = t1.cafe_no ");
		SQL.append("group by t1.cafe_no,t1.cafe_name,t1.cafe_address1");
		SQL.append(",t1.cafe_address2,t1.cafe_tel,t1.open_time,t1.close_time,t4.cfile_path ");
		SQL.append("having (cfile_path in ");
		SQL.append("(select cfile_path from (select ROW_number() OVER(partition By cafe_no Order by cafe_no,cfile_path) Ran ,cafe_no,cfile_path from cafefile) ");
		SQL.append("where RAn =1) or cfile_path is NULL) ");
		SQL.append("and t1.cafe_address1 like ?");
		return 	jt.query(SQL.toString(), new BeanPropertyRowMapper<CafeVO>(CafeVO.class),keyword);
	}

	/**
	 * 이름으로 카페 검색
	 * @param keyword
	 * @return
	 */
	@Override
	public List<CafeVO> searchname(String keyword) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select t1.cafe_no,t1.cafe_name,t1.cafe_address1");
		SQL.append(",t1.cafe_address2,t1.cafe_tel,t1.open_time,t1.close_time,t4.cfile_path,");
		SQL.append("LiSTAGG(tag_name,',') Within group(order by t1.cafe_no) as tagnames ");
		SQL.append("from cafe t1 inner join cafetags t2 on t2.cafe_no = t1.cafe_no ");
		SQL.append("inner join tags t3 on t3.tag_no = t2.tag_no ");
		SQL.append("LEFT OUTER join cafefile t4 on t4.cafe_no = t1.cafe_no ");
		SQL.append("group by t1.cafe_no,t1.cafe_name,t1.cafe_address1");
		SQL.append(",t1.cafe_address2,t1.cafe_tel,t1.open_time,t1.close_time,t4.cfile_path ");
		SQL.append("having (cfile_path in ");
		SQL.append("(select cfile_path from (select ROW_number() OVER(partition By cafe_no Order by cafe_no,cfile_path) Ran ,cafe_no,cfile_path from cafefile) ");
		SQL.append("where RAn =1) or cfile_path is NULL) ");
		SQL.append("and t1.cafe_name like ?");
		return 	jt.query(SQL.toString(), new BeanPropertyRowMapper<CafeVO>(CafeVO.class),keyword);
	}

	/**
	 * 카페 정보 불러오기
	 * @param cafe_no
	 * @return
	 */
	@Override
	public CafeVO cafeinfo(int cafe_no) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select * from cafe where cafe_no =?");
		CafeVO cafevo = jt.queryForObject(SQL.toString(), new BeanPropertyRowMapper<CafeVO>(CafeVO.class),cafe_no);
		gettagname(cafevo);
		SQL.setLength(0);
		SQL.append("select count(*) from cafefile where cafe_no = ?");
		int r = jt.queryForObject(SQL.toString(),Integer.class,cafe_no);
		if(r!=0) {
			getcafefile(cafevo);
		}
		SQL.setLength(0);
		SQL.append("select count(*) from menu where cafe_no = ?");
		int re = jt.queryForObject(SQL.toString(), Integer.class,cafe_no);
		if(re!=0) {
			getcafemenufile(cafevo);
		}
		return cafevo;
	}
	
	
	private void gettagname(CafeVO cafevo) {
		String SQL = "select tag_name from tags inner join cafetags on tags.tag_no = cafetags.tag_no inner join cafe on cafetags.cafe_no = cafe.cafe_no where cafe.cafe_no =?";
		List<String> list = jt.queryForList(SQL,String.class,cafevo.getCafe_no());
		cafevo.setTag_name(list);
	}
	private void getcafefile(CafeVO cafevo) {
		String SQL = "select * from cafefile where cafe_no = ?";
		List<CfileVO> list = jt.query(SQL, new BeanPropertyRowMapper<CfileVO>(CfileVO.class),cafevo.getCafe_no());
		cafevo.setCfilevo(list);
	}
	private void getcafemenufile(CafeVO cafevo) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select menu_name,menu_price,menu_content,category_name,menu_price,cafe_no,mfile_path ");
		SQL.append("from menu t1 left outer join menufile t2 on t2.menu_no = t1.menu_no ");
		SQL.append("inner join categorys t3 on t3.category_no = t1.menu_category ");
		SQL.append("where cafe_no = ?");
		List<MenuVO> list = jt.query(SQL.toString(), new BeanPropertyRowMapper<MenuVO>(MenuVO.class),cafevo.getCafe_no());
		cafevo.setMenuvo(list);
	}
	
	/**
	 * 북마크 등록 여부
	 * @param cafevo
	 * @param member_id
	 * @return
	 */
	@Override
	public boolean isbookmark(CafeVO cafevo, String member_id) {
		boolean result = false;
		String SQL = "select count(*) from bookmarks where cafe_no = ? and member_id = ?";
		int r = jt.queryForObject(SQL,Integer.class ,cafevo.getCafe_no(),member_id);
		if(r!=0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean iscafeinfo(int cafe_no) {
		String SQL = "select count(*) from cafe where cafe_no = ?";
		boolean result = false;
		int r = jt.queryForObject(SQL, Integer.class,cafe_no);
		if(r!=0) {
			result = true;
		}
		return result;
	}


}
