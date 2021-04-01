package com.kh.cafeinme.menu.dao;


import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import com.kh.cafeinme.menu.vo.CategoryVO;
import com.kh.cafeinme.menu.vo.MenuVO;
import com.kh.cafeinme.menu.vo.MenufileVO;
import com.kh.cafeinme.mycafe.svc.FilecodeSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MenuDAOImpl implements MenuDAO{
	// 3.12 추가
	private final String PATH = "C:\\Users\\dimpl\\Documents\\test\\cafeinme\\src\\main\\webapp\\WEB-INF\\resources\\img\\";
	private final JdbcTemplate jt;
	private final FilecodeSVC fs;
	private final SqlSession sqlSession;
	
	/**
	 * 메뉴 수정
	 * @param list
	 * @param member_id
	 * @return
	 */
	@Override
	@Transactional
	public int[] updatemenu(List<MenuVO> list,String member_id) {
		
		String SQL = "update menu set menu_name = ?,menu_price=?,menu_category=?,menu_content=? where menu_no=?";
		jt.batchUpdate(SQL,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, list.get(i).getMenu_name());
				ps.setInt(2, list.get(i).getMenu_price());
				ps.setInt(3, list.get(i).getMenu_category());
				ps.setString(4, list.get(i).getMenu_content());
				ps.setInt(5, list.get(i).getMenu_no());
			}
			
			@Override
			public int getBatchSize() {
				
				return list.size();
			}
		});
		for(int i=0; i<list.size(); i++) {
			if(!list.get(i).getFile().isEmpty()) {
				deletemenufile(list.get(i),member_id);
				insertmenufile(list.get(i),member_id);
			}
		}
		return null;
	}

	/**
	 * 메뉴 파일 삭제
	 * @param menuvo
	 * @param member_id
	 */
	private void deletemenufile(MenuVO menuvo,String member_id) {
		if(iscafefile(menuvo.getMenu_no())) {
			String SQL = "select * from menufile where menu_no = ?";
			MenufileVO mfilevo = jt.queryForObject(SQL, new BeanPropertyRowMapper<MenufileVO>(MenufileVO.class),menuvo.getMenu_no());
			StringBuilder filepath = new StringBuilder();
			filepath.append(PATH);
			filepath.append(member_id);
			filepath.append("\\cafemenu\\");
			String filename = fs.recode(mfilevo.getMfile_path());
			filepath.append(filename);
			File file = new File(filepath.toString());
			if(file.exists()) {   
				file.delete();
			}
			String SQL2 = "delete menufile where menu_no = ?";
			jt.update(SQL2,menuvo.getMenu_no());
		}
		
	}
	
	/**
	 * 메뉴 파일 등록
	 * @param menuvo
	 * @param member_id
	 */
	// (2021 03 12 수정)
	private void insertmenufile(MenuVO menuvo,String member_id) {
		String SQL = "insert into menufile values(MENUFILE_MFILE_NO_SEQ.nextval,?,?,?,?,?,?)";
		StringBuilder filepath = new StringBuilder();
		filepath.append(PATH);
		filepath.append(member_id);
		filepath.append("\\cafemenu");
		String filename = fs.code(menuvo.getFile().getContentType());
		menuvo.setMfile_changename(filename);
		File file = new File(filepath.toString(),filename);
		if ( ! new File(filepath.toString()).exists()) {
	    new File(filepath.toString()).mkdirs();
	}
	try {
	  FileCopyUtils.copy(menuvo.getFile().getBytes(), file);
	  
	} catch(Exception e) {
	  e.printStackTrace();
	  
	}
	StringBuilder mfile_path = new StringBuilder();
	mfile_path.append("/");
	mfile_path.append(member_id);
	mfile_path.append("/cafemenu/");
	mfile_path.append(filename);
	jt.update(SQL,menuvo.getFile().getOriginalFilename(),menuvo.getMfile_changename(),menuvo.getFile().getSize(),menuvo.getFile().getContentType(),mfile_path,menuvo.getMenu_no());
	}
	
	
	/**
	 * 메뉴 추가
	 * @param list
	 * @param member_id
	 * @return
	 */
	// (2021 03 12 수정)
	@Override
	@Transactional
	public int[] insertmenu(List<MenuVO> list, String member_id) {
		int cafe_no = mycafeno(member_id);
		String SQL = "insert into menu values(?,?,?,?,?,?,?)";
		String SQL2 = "select MENU_MENU_NO_SEQ.nextval from dual";
		for(int i=0; i<list.size(); i++) {
			int r = jt.queryForObject(SQL2, Integer.class);
			list.get(i).setMenu_no(r);
		}
		jt.batchUpdate(SQL,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, list.get(i).getMenu_no());
				ps.setString(2,list.get(i).getMenu_name());
				ps.setInt(3,list.get(i).getMenu_price());
				ps.setString(4,list.get(i).getMenu_content());
				ps.setInt(5,list.get(i).getMenu_category());
				ps.setInt(6, 0);
				ps.setInt(7, cafe_no);
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
		for(int i=0; i<list.size(); i++) {
			if(!list.get(i).getFile().isEmpty()) {
				insertmenufile(list.get(i),member_id);
			}
		}
		return null;
	}
	
	/**
	 * 메뉴 삭제
	 * @param menu_no
	 * @param member_id
	 * @return
	 */
	@Override
	public int delmenu(int menu_no,String member_id) {
		String SQL = "select * from menufile where menu_no = ?";
		String SQL2 = "delete menu where menu_no =?";
		if(iscafefile(menu_no)) {
			MenufileVO mfilevo = jt.queryForObject(SQL, new BeanPropertyRowMapper<MenufileVO>(MenufileVO.class),menu_no);
			StringBuilder filepath = new StringBuilder();
			filepath.append(PATH);
			filepath.append(member_id);
			filepath.append("\\cafemenu\\");
			String filename = fs.recode(mfilevo.getMfile_path());
			filepath.append(filename);
			log.info(filepath.toString());
			File file = new File(filepath.toString());
			if(file.exists()) {
				file.delete();
			}

		}
		
		return jt.update(SQL2,menu_no);
	}
	
	/**
	 * 등록된 메뉴 목록 불러오기
	 * @param member_id
	 * @return
	 */
	// (2021 03 12 수정)
	@Override
	public List<MenuVO> getmymenu(String member_id) {
		int cafe_no = mycafeno(member_id);
		StringBuilder SQL = new StringBuilder();
		SQL.append("select t1.menu_no,menu_name,menu_price,menu_content,menu_category,category_name,menu_price,cafe_no,mfile_path,t1.menu_onlinesale ");
		SQL.append("from menu t1 left outer join menufile t2 on t2.menu_no = t1.menu_no ");
		SQL.append("inner join categorys t3 on t3.category_no = t1.menu_category ");
		SQL.append("where cafe_no = ?");
		List<MenuVO> list = new ArrayList<MenuVO>();
		if(iscafemenu(cafe_no)) {
			list = jt.query(SQL.toString(), new BeanPropertyRowMapper<MenuVO>(MenuVO.class),cafe_no);
		}
		return list;
	}
	
	/**
	 * 멤버 아이디에 해당하는 카페넘버
	 * @param member_id
	 * @return
	 */
	private int mycafeno(String member_id) {
		String SQL = "select cafe_no from cafe where cafe_ownerid = ?";
		return jt.queryForObject(SQL, Integer.class,member_id);
	}
	
	/**
	 * 카페 메뉴 여부 확인
	 * @param cafe_no
	 * @return
	 */
	private boolean iscafemenu(int cafe_no) {
		boolean result = false;
		String SQL = "select count(*) from menu where cafe_no =?";
		int r = jt.queryForObject(SQL, Integer.class,cafe_no);
		if(r!=0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 카페 파일 여부 확인
	 * @param menu_no
	 * @return
	 */
	private boolean iscafefile(int menu_no) {
		boolean result = false;
		String SQL = "select count(*) from menufile where menu_no =?";
		int r = jt.queryForObject(SQL, Integer.class,menu_no);
		if(r!=0) {
			result = true;
		}
		return result;
	}
	
	//메뉴 1개 가져오기(21.03.11 수정)
	public MenuVO getmyonemenu(int menu_no) {
		return sqlSession.selectOne("mappers.menuDAO-mapper.getMyonemenu", menu_no);
	}

	/**
	 * 온라인 판매 메뉴 등록
	 * @param menu_no
	 * @param onlinesale
	 * @return
	 */
	@Override
	public int updateonline(int menu_no, int onlinesale) {
		String SQL = "update menu set menu_onlinesale = ? where menu_no =?";
		
		return jt.update(SQL,onlinesale,menu_no);
	}
	
	/**
	 * 카테고리 불러오기(21.03.11 수정)
	 * @return
	 */
	@Override
	public List<CategoryVO> getCategory() {
		return sqlSession.selectList("mappers.menuDAO-mapper.getCategory");
	}
	@Override
	public List<CategoryVO> getCategory(int cafe_no) {
		return sqlSession.selectList("mappers.menuDAO-mapper.getCategory2", cafe_no);
	}
	@Override
	public List<CategoryVO> getCategoryOnlinesale(int cafe_no) {
		return sqlSession.selectList("mappers.menuDAO-mapper.getCategory3", cafe_no);
	}

	/**
	 * 메뉴 불러오기(21.03.11 수정)
	 * @param cafe_no
	 * @return
	 */
	@Override
	public List<MenuVO> getOnlinesaleMenu(int cafe_no) {
		return sqlSession.selectList("mappers.menuDAO-mapper.getOnlinesaleMenu", cafe_no);
	}
	

}
