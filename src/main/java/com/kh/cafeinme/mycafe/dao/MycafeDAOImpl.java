package com.kh.cafeinme.mycafe.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.kh.cafeinme.menu.vo.MenuVO;
import com.kh.cafeinme.mycafe.svc.FilecodeSVC;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.mycafe.vo.CfileVO;
import com.kh.cafeinme.mycafe.vo.ModifyCafeVO;
import com.kh.cafeinme.mycafe.vo.TagVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;




@Repository
@RequiredArgsConstructor
@Slf4j
public class MycafeDAOImpl implements MycafeDAO {
	//3.12추가
private final String PATH = "C:\\Users\\dimpl\\Documents\\test\\cafeinme\\src\\main\\webapp\\WEB-INF\\resources\\img\\";
private final JdbcTemplate jt;
private final FilecodeSVC fs;
private final SqlSession sqlSession;
// 카페등록
	@Override
	@Transactional
	public int joinMycafe(CafeVO cafevo) {
		// 시퀀스번호 받아온걸 cafevo에 cafe_no로 저장
		cafevo.setCafe_no(getcafeseq());
		String SQL = "insert into cafe values(?,?,?,?,?,?,?,?,?,?,?)";
		int r = jt.update(SQL,cafevo.getCafe_no()
				,cafevo.getCafe_ownerid(),cafevo.getCafe_name()
				,cafevo.getBn(),cafevo.getCafe_address1()
				,cafevo.getCafe_address2(),cafevo.getCafe_tel()
				,cafevo.getOpen_time(),cafevo.getClose_time(),cafevo.getCafe_content(),0);

			
			insertMycafetags(cafevo);

		//카페이미지 파일이 있는경우에만 이미지등록
		if(cafevo.getFiles().get(0).getSize()!=0) {
			

			insertMycafefiles(cafevo);
		}
		return r;
	}
// 카페시퀀스번호 받아오기
	private int getcafeseq() {
		String SQL = "select cafe_cafe_no_seq.nextval from dual";
		int r = jt.queryForObject(SQL, Integer.class);
		return r;
	}
// 카페태그 등록
	private int[] insertMycafetags(CafeVO cafevo) {
		String SQL = "insert into cafetags values(?,?)";
		int[] r = jt.batchUpdate(SQL, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, cafevo.getCafe_no());
				ps.setInt(2, cafevo.getTag_no().get(i));
				
			}
			
			@Override
			public int getBatchSize() {
				int i = cafevo.getTag_no().size();
				return i;
			}
		});
		return r;
	}
	// 카페이미지파일 등록
	// (2021.03.12 수정)
		private int[] insertMycafefiles(CafeVO cafevo) {
			String SQL = "insert into cafefile values(CAFEFILE_CFILE_NO_SEQ.nextval,?,?,?,?,?,?)";
			StringBuilder uploadPath = new StringBuilder();
			uploadPath.append(PATH);
			uploadPath.append(cafevo.getCafe_ownerid());
			uploadPath.append("\\cafeimg");
			List<CfileVO> list = new ArrayList<CfileVO>();
			for(int i=0; i<cafevo.getFiles().size(); i++) {
				String fileName = fs.code(cafevo.getFiles().get(i).getContentType());
				CfileVO cfilevo = new CfileVO();
				File target = new File(uploadPath.toString(), fileName);
				//경로 생성
				if ( ! new File(uploadPath.toString()).exists()) {
				    new File(uploadPath.toString()).mkdirs();
				}
				try {
				  FileCopyUtils.copy(cafevo.getFiles().get(i).getBytes(), target);
				  
				} catch(Exception e) {
				  e.printStackTrace();
				  
				}
				StringBuilder file_path = new StringBuilder();
				file_path.append(cafevo.getCafe_ownerid());
				file_path.append("/cafeimg/");
				file_path.append(fileName);
				cfilevo.setCafe_no(cafevo.getCafe_no());
				cfilevo.setCfile_origin_name(cafevo.getFiles().get(i).getOriginalFilename());
				cfilevo.setCfile_change_name(fileName);
				cfilevo.setCfile_path(file_path.toString());
				cfilevo.setCfile_size(cafevo.getFiles().get(i).getSize());
				cfilevo.setCfile_type(cafevo.getFiles().get(i).getContentType());
				list.add(cfilevo);
			}
			cafevo.setCfilevo(list);
			int[] r = jt.batchUpdate(SQL, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, cafevo.getCfilevo().get(i).getCfile_origin_name());
					ps.setString(2, cafevo.getCfilevo().get(i).getCfile_change_name());
					ps.setLong(3, cafevo.getCfilevo().get(i).getCfile_size());
					ps.setString(4, cafevo.getCfilevo().get(i).getCfile_type());
					ps.setString(5, cafevo.getCfilevo().get(i).getCfile_path());
					ps.setInt(6, cafevo.getCfilevo().get(i).getCafe_no());
					
				}
				
				@Override
				public int getBatchSize() {
					int i = cafevo.getCfilevo().size();
					return i;
				}
			});
			return r;
	}
	//가입시 태그가져오는 메서드
	@Override
	public List<TagVO> gettags() {
		String SQL = "select * from tags order by tag_no";
		List<TagVO> tags = jt.query(SQL, new BeanPropertyRowMapper<TagVO>(TagVO.class));
		return tags;
	}
	//내카페보기
	@Override
	@Transactional(readOnly=true)
	public CafeVO getmycafe(String member_id) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select * from cafe where cafe_ownerid = ?");
		CafeVO cafevo = jt.queryForObject(SQL.toString(), new BeanPropertyRowMapper<CafeVO>(CafeVO.class),member_id);
		cafevo.setTag_name(getmycafetag(member_id));
		if(ismycafefile(cafevo.getCafe_no())) {
			getmycafefile(cafevo);
		}
		if(ismycafemenu(cafevo.getCafe_no())) {
			getmymenu(cafevo);
		}
		return cafevo;
	}
	
	/**
	 * 카페 파일 존재 여부 확인
	 * @param cafe_no
	 * @return
	 */
	private boolean ismycafefile(int cafe_no) {
		boolean result = false;
		String SQL = "select count(*) from cafefile where cafe_no = ?";
		int r = jt.queryForObject(SQL, Integer.class,cafe_no);
		if(r!=0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 내 카페 파일 보기
	 * @param cafevo
	 */
	private void getmycafefile(CafeVO cafevo){
		String SQL = "select * from cafefile where cafe_no = ?";
		List<CfileVO> list = jt.query(SQL, new BeanPropertyRowMapper<CfileVO>(CfileVO.class),cafevo.getCafe_no());
		cafevo.setCfilevo(list);
	}
	
	/**
	 * 내 카페 태그 리스트
	 * @param id
	 * @return
	 */
	private List<String> getmycafetag(String id){
	String SQL = "select tag_name from tags inner join cafetags on tags.tag_no = cafetags.tag_no inner join cafe on cafetags.cafe_no = cafe.cafe_no where cafe_ownerid = ?";
	List<String> list = jt.queryForList(SQL, String.class,id);

		return list;
	}
	
	/**
	 * 내 카페 등록 여부 확인
	 * @param cafe_no
	 * @return
	 */
	private boolean ismycafemenu(int cafe_no) {
		boolean result = false;
		String SQL = "select count(*) from menu where cafe_no= ?";
		int r = jt.queryForObject(SQL, Integer.class,cafe_no);
		if(r!=0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 내 카페 메뉴 보기
	 * @param cafevo
	 */
	private void getmymenu(CafeVO cafevo) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select t1.menu_no,menu_name,menu_price,menu_content,menu_category,category_name,menu_price,cafe_no,mfile_path ");
		SQL.append("from menu t1 left outer join menufile t2 on t2.menu_no = t1.menu_no ");
		SQL.append("inner join categorys t3 on t3.category_no = t1.menu_category ");
		SQL.append("where cafe_no = ?");
		List<MenuVO> list = jt.query(SQL.toString(), new BeanPropertyRowMapper<MenuVO>(MenuVO.class),cafevo.getCafe_no());
		cafevo.setMenuvo(list);
	}
	
	//내 카페 수정
	//3.12 수정
	@Override
	@Transactional
	public int modifymycafe(ModifyCafeVO cafevo,String member_id) {
		String SQL ="update cafe set CAFE_NAME = ?, CAFE_ADDRESS1 = ?, CAFE_ADDRESS2 = ?, CAFE_TEL =?, OPEN_TIME = ?, CLOSE_TIME = ?, CAFE_CONTENT =? where cafe_no = ?";
		int cafe_no = mycafeno(member_id);
		int r = jt.update(SQL,cafevo.getCafe_name(),cafevo.getCafe_address1(),cafevo.getCafe_address2(),cafevo.getCafe_tel(),cafevo.getOpen_time(),cafevo.getClose_time(),cafevo.getCafe_content(),cafe_no);
		if(cafevo.getDelfile()!=null) {
			for(int i=0; i<cafevo.getDelfile().size(); i++) {
				delmyfile(cafevo.getDelfile().get(i).getCfile_no(),member_id);
			}
		}
		if(!cafevo.getFiles().get(0).isEmpty()) {
			insertMymodifyfile(cafevo, member_id,cafe_no);
			
		}
		deltags(cafe_no);
		insertMycafetags(cafevo, cafe_no);
		return r;
	}
	private int[] insertMymodifyfile(ModifyCafeVO cafevo,String member_id,int cafe_no) {
		String SQL = "insert into cafefile values(CAFEFILE_CFILE_NO_SEQ.nextval,?,?,?,?,?,?)";
		StringBuilder uploadPath = new StringBuilder();
		uploadPath.append(PATH);
		uploadPath.append(member_id);
		uploadPath.append("\\cafeimg");
		List<CfileVO> list = new ArrayList<CfileVO>();
		for(int i=0; i<cafevo.getFiles().size(); i++) {
			String fileName = fs.code(cafevo.getFiles().get(i).getContentType());
			CfileVO cfilevo = new CfileVO();
			File target = new File(uploadPath.toString(), fileName);
			//경로 생성
			if ( ! new File(uploadPath.toString()).exists()) {
			    new File(uploadPath.toString()).mkdirs();
			}
			try {
			  FileCopyUtils.copy(cafevo.getFiles().get(i).getBytes(), target);
			  
			} catch(Exception e) {
			  e.printStackTrace();
			  
			}
			StringBuilder file_path = new StringBuilder();
			file_path.append(member_id);
			file_path.append("/cafeimg/");
			file_path.append(fileName);
			cfilevo.setCafe_no(cafe_no);
			cfilevo.setCfile_origin_name(cafevo.getFiles().get(i).getOriginalFilename());
			cfilevo.setCfile_change_name(fileName);
			cfilevo.setCfile_path(file_path.toString());
			cfilevo.setCfile_size(cafevo.getFiles().get(i).getSize());
			cfilevo.setCfile_type(cafevo.getFiles().get(i).getContentType());
			list.add(cfilevo);
		}
		int[] r = jt.batchUpdate(SQL,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, list.get(i).getCfile_origin_name());
				ps.setString(2, list.get(i).getCfile_change_name());
				ps.setLong(3, list.get(i).getCfile_size());
				ps.setString(4, list.get(i).getCfile_type());
				ps.setString(5, list.get(i).getCfile_path());
				ps.setInt(6, list.get(i).getCafe_no());
				
			}
			
			@Override
			public int getBatchSize() {
				
				return list.size();
			}
		});
		return r;
	}
	private int[] insertMycafetags(ModifyCafeVO cafevo,int cafe_no) {
		String SQL = "insert into cafetags values(?,?)";
		int[] r = jt.batchUpdate(SQL, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, cafe_no);
				ps.setInt(2, cafevo.getTag_no().get(i));
				
			}
			
			@Override
			public int getBatchSize() {
				int i = cafevo.getTag_no().size();
				return i;
			}
		});
		return r;
	}
	//태그 삭제
	private int deltags(int cafe_no) {
		String SQL = "delete cafetags where cafe_no =?";
	
		return jt.update(SQL,cafe_no);
	}
	private int mycafeno(String member_id) {
		String SQL = "select cafe_no from cafe where cafe_ownerid = ?";
		return jt.queryForObject(SQL, Integer.class,member_id);
	}
	//내 카페 삭제
	@Override
	public int delmycafe(String member_id) {
		String SQL = "delete cafe where cafe_ownerid =?";
	
			StringBuilder filepath = new StringBuilder();
			filepath.append(PATH);
			filepath.append(member_id);
			delmyfileall(filepath.toString());
			return jt.update(SQL,member_id);
	}
	
	//모든 파일 삭제
	private void delmyfileall(String filepath) {
		File folder = new File(filepath);
    try {
	if(folder.exists()){
    File[] folder_list = folder.listFiles(); 
			
	for (int i = 0; i < folder_list.length; i++) {
	    if(folder_list[i].isFile()) {
		folder_list[i].delete();
	    }else {
	   delmyfileall(folder_list[i].getPath());
	    }
	    folder_list[i].delete();
	 }
	 folder.delete(); 
       }
   } catch (Exception e) {
	e.getStackTrace();
   }
	}
	
	//카페 파일 삭제
	@Override
	public int delmyfile(int cfile_no,String member_id) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select * from cafefile where cfile_no =?");
		CfileVO cfilevo = jt.queryForObject(SQL.toString(), new BeanPropertyRowMapper<CfileVO>(CfileVO.class),cfile_no);
		StringBuilder filepath = new StringBuilder();
		filepath.append(PATH);
		filepath.append(member_id);
		filepath.append("\\cafeimg\\");
		filepath.append(cfilevo.getCfile_change_name());
		SQL.setLength(0);
		SQL.append("delete cafefile where cfile_no =?");
		
		File file = new File(filepath.toString());
		if(file.exists()) {
		file.delete();}
		return jt.update(SQL.toString(),cfile_no);
	}
	
	//회원 카페 등록 여부
	@Override
	public boolean ishavecafe(String member_id) {
		boolean flag = false;
		String SQL = "select count(*) from cafe where cafe_ownerid = ?";
		int r = jt.queryForObject(SQL, Integer.class,member_id);
		if(r!=0) {
			flag = true;
		}
		return flag;
	}
	
	//사업자 번호 조회
	@Override
	public boolean ishavebn(String bn) {
		boolean flag = false;
		String SQL = "select count(*) from cafe where bn =?";
		int r = jt.queryForObject(SQL, Integer.class,bn);
		if(r!=0){
			flag = true;
		}
		return flag;
	}
	
	// 온라인판매여부(21.3.5에 추가)
	@Override
	public boolean isOnlinesale(int cafe_no) {
		boolean flag = false;
		String sql = "select count(*) from CAFE where cafe_no = ? and cafe_onlinesale = 1";
		int result = jt.queryForObject(sql, Integer.class, cafe_no);
		if(result != 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 온라인 판매 여부 확인
	 */
	@Override
	public boolean isonlinesale(String member_id) {
		boolean result = false;
		String SQL ="select cafe_onlinesale from cafe where cafe_ownerid =?";
		int r = jt.queryForObject(SQL, Integer.class,member_id);
		if(r!=0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 사업자 번호 확인
	 */
	@Override
	public boolean isbn(String bn,String member_id) {
		boolean result = false;
		String SQL = "select BN from cafe where cafe_ownerid = ?";
		String rb = jt.queryForObject(SQL, String.class,member_id);
		if(rb.equals(bn)) {
			result = true;
		}
		return result;
	}

	/**
	 * 카페사장아이디 가져오기(카페번호), 3월 22일에 추가
	 * @param cafe_no
	 * @return
	 */
	@Override
	public String getCafeOwnerID(int cafe_no) {
		return sqlSession.selectOne("mappers.CafeDAO-mapper.getCafeOwnerID", cafe_no);
	}
}
