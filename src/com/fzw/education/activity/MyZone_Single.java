package com.fzw.education.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fzw.education.R;
import com.fzw.education.R.id;
import com.fzw.education.R.layout;
import com.fzw.education.R.menu;
import com.fzw.education.db.ImportDatabase;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MyZone_Single extends Activity implements OnGestureListener{
	private int id=1;
	private TextView zhangjiequestion=null;
	private RadioGroup btnGrp=null;
	private RadioButton btnA,btnB,btnC,btnD;
	private TextView zhangjiejiexi=null;
	private String questionString=null;
	private ImportDatabase impd=null;
	private SQLiteDatabase sqldb=null;
	private SQLiteDatabase mysqldb=null;
	private Cursor cursor=null;
	private GestureDetector gDetector=null;
	private ActionBar actionBar=null;
	private int Pointer=0,Length=0;
	private List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monidanxuan);
		init();
		id=getIntent().getExtras().getInt("MYZONE_ID");
		impd=new ImportDatabase(this);
		sqldb=impd.getDB();
		if(id==1){
			//�ҵ��ղأ���ѡ�⣩
			try {
				cursor=sqldb.rawQuery("select * from collect where type=0", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("MyZone_single--->���ݿ��ȡʧ��1");
			}
		}
		else if(id==3){
			//�ҵĴ��⣨��ѡ��
			try {
				cursor=sqldb.rawQuery("select * from wrong where type=0", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("MyZone_single--->���ݿ��ȡʧ��2");
			}
		}
		if(cursor!=null&&cursor.getCount()!=0){
			Length=cursor.getCount();
			for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("question", cursor.getString(cursor.getColumnIndex("question")));
				map.put("A", cursor.getString(cursor.getColumnIndex("A")));
				map.put("B", cursor.getString(cursor.getColumnIndex("B")));
				map.put("C", cursor.getString(cursor.getColumnIndex("C")));
				map.put("D", cursor.getString(cursor.getColumnIndex("D")));
				map.put("E", cursor.getString(cursor.getColumnIndex("E")));
				map.put("answer", cursor.getString(cursor.getColumnIndex("answer")));
				map.put("jiexi", cursor.getString(cursor.getColumnIndex("jiexi")));
				
				list.add(map);
			}
				questionString=list.get(Pointer).get("question");
				zhangjiequestion.setText(questionString);
				btnA.setText(list.get(Pointer).get("A"));
				btnB.setText(list.get(Pointer).get("B"));
				btnC.setText(list.get(Pointer).get("C"));
				btnD.setText(list.get(Pointer).get("D"));
				
				btnGrp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						int flag=1;
						String answer=list.get(Pointer).get("answer");
						System.out.println("answer-->"+answer);
						btnA.setClickable(false);
						btnB.setClickable(false);
						btnC.setClickable(false);
						btnD.setClickable(false);
						switch (checkedId) {
						case R.id.btnA:
							System.out.println("A");
							if(answer.equals("A")){
								//Toast.makeText(MyZone_Single.this, "�ش���ȷ", Toast.LENGTH_SHORT).show();
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
							}
							else {
								System.out.println("����");
								btnA.setTextColor(Color.RED);
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
							}
							break;

						case R.id.btnB:
							System.out.println("B");
							if(answer.equals("B")){
								//Toast.makeText(MyZone_Single.this, "�ش���ȷ", Toast.LENGTH_SHORT).show();
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
							}else {
								System.out.println("����");
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
								
								btnB.setTextColor(Color.RED);
								
							}
							break;
						case R.id.btnC:
							System.out.println("C");
							if(answer.equals("C")){
								//Toast.makeText(MyZone_Single.this, "�ش���ȷ", Toast.LENGTH_SHORT).show();
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
							}else {
								System.out.println("����");
								btnC.setTextColor(Color.RED);
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
							}
							break;
						case R.id.btnD:
							System.out.println("D");
							if(answer.equals("D")){
								//Toast.makeText(MyZone_Single.this, "�ش���ȷ", Toast.LENGTH_SHORT).show();
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
							}else {
								System.out.println("����");
								btnD.setTextColor(Color.RED);
								zhangjiejiexi.setText(list.get(Pointer).get("jiexi"));
							}
							break;
						}
						
					}
					//��������
					
				});
				gDetector=new GestureDetector(this);

		}
		else {
			btnGrp.setVisibility(View.INVISIBLE);
			if(id==1){
				Toast.makeText(this, "��ľ���κ��ղ�", Toast.LENGTH_LONG).show();
			}
			else {
				Toast.makeText(this, "��ľ���κδ���", Toast.LENGTH_LONG).show();
			}
			
		}
		
	}
	
	private void init(){
		zhangjiequestion=(TextView)findViewById(R.id.zhangjie_question);
		btnGrp=(RadioGroup)findViewById(R.id.BtnGrp);
		btnA=(RadioButton)findViewById(R.id.btnA);
		btnB=(RadioButton)findViewById(R.id.btnB);
		btnC=(RadioButton)findViewById(R.id.btnC);
		btnD=(RadioButton)findViewById(R.id.btnD);
		zhangjiejiexi=(TextView)findViewById(R.id.zhangjie_jiexi);
		actionBar=this.getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if(e1.getX()-e2.getX()>100){/*
			//���һ�
			if(cursor.isLast()){
				Toast.makeText(this, "�����Ѿ�û������", Toast.LENGTH_SHORT).show();
			}else {
				cursor.moveToNext();
				btnGrp.clearCheck();
				questionString=cursor.getString(cursor.getColumnIndex("question"));
				zhangjiequestion.setText(questionString);
				btnA.setText(cursor.getString(cursor.getColumnIndex("A")));
				btnB.setText(cursor.getString(cursor.getColumnIndex("B")));
				btnC.setText(cursor.getString(cursor.getColumnIndex("C")));
				btnD.setText(cursor.getString(cursor.getColumnIndex("D")));
				btnA.setTextColor(Color.BLACK);
				btnB.setTextColor(Color.BLACK);
				btnC.setTextColor(Color.BLACK);
				btnD.setTextColor(Color.BLACK);
				btnA.setClickable(true);
				btnB.setClickable(true);
				btnC.setClickable(true);
				btnD.setClickable(true);
				zhangjiejiexi.setText("");

			}
			
		*/	if(Pointer==Length-1){
			Toast.makeText(this, "�����Ѿ�û����", Toast.LENGTH_SHORT).show();
			}
			else {
				++Pointer;
				btnGrp.clearCheck();
				questionString=list.get(Pointer).get("question");
				zhangjiequestion.setText(questionString);
				btnA.setText(list.get(Pointer).get("A"));
				btnB.setText(list.get(Pointer).get("B"));
				btnC.setText(list.get(Pointer).get("C"));
				btnD.setText(list.get(Pointer).get("D"));
				btnA.setTextColor(Color.BLACK);
				btnB.setTextColor(Color.BLACK);
				btnC.setTextColor(Color.BLACK);
				btnD.setTextColor(Color.BLACK);
				btnA.setClickable(true);
				btnB.setClickable(true);
				btnC.setClickable(true);
				btnD.setClickable(true);
				zhangjiejiexi.setText("");
			}
			}
		else if(e1.getX()-e2.getX()<-100){/*
			//��ǰ����
			System.out.println("ǰһ��");
			if(cursor.isFirst()){
				Toast.makeText(this, "ǰ��û����", Toast.LENGTH_SHORT).show();
			}
			else {
				cursor.moveToPrevious();
				System.out.println("cursor ��ǰ�ƶ�һ��");
				btnGrp.clearCheck();
				questionString=cursor.getString(cursor.getColumnIndex("question"));
				zhangjiequestion.setText(questionString);
				btnA.setText(cursor.getString(cursor.getColumnIndex("A")));
				btnB.setText(cursor.getString(cursor.getColumnIndex("B")));
				btnC.setText(cursor.getString(cursor.getColumnIndex("C")));
				btnD.setText(cursor.getString(cursor.getColumnIndex("D")));
				btnA.setTextColor(Color.BLACK);
				btnB.setTextColor(Color.BLACK);
				btnC.setTextColor(Color.BLACK);
				btnD.setTextColor(Color.BLACK);
				btnA.setClickable(true);
				btnB.setClickable(true);
				btnC.setClickable(true);
				btnD.setClickable(true);
				zhangjiejiexi.setText("");
				
			}
		*/	if(Pointer==0){
			Toast.makeText(this, "ǰ���Ѿ�û����", Toast.LENGTH_SHORT).show();
			}
			else {
				--Pointer;
				btnGrp.clearCheck();
				questionString=list.get(Pointer).get("question");
				zhangjiequestion.setText(questionString);
				btnA.setText(list.get(Pointer).get("A"));
				btnB.setText(list.get(Pointer).get("B"));
				btnC.setText(list.get(Pointer).get("C"));
				btnD.setText(list.get(Pointer).get("D"));
				btnA.setTextColor(Color.BLACK);
				btnB.setTextColor(Color.BLACK);
				btnC.setTextColor(Color.BLACK);
				btnD.setTextColor(Color.BLACK);
				btnA.setClickable(true);
				btnB.setClickable(true);
				btnC.setClickable(true);
				btnD.setClickable(true);
				zhangjiejiexi.setText("");
			}
			
			}
		return true;
	}

	/*@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		super.dispatchTouchEvent(ev);
		return gDetector.onTouchEvent(ev);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return this.gDetector.onTouchEvent(event);
	}*/

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.my_zone, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.onTouchEvent(event);
		return this.gDetector.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		 super.dispatchTouchEvent(ev);
		 return gDetector.onTouchEvent(ev);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			System.out.println("����");
			ScrollView scrollView=(ScrollView)getLayoutInflater()
			.inflate(R.layout.dialog_zhangjielianxi, null);
			AlertDialog.Builder aBuilder=new AlertDialog.Builder(this);
			aBuilder.setTitle("��ܰ��ʾ");
			aBuilder.setView(scrollView);
			aBuilder.setNegativeButton("ȡ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			aBuilder.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			aBuilder.show();
			break;

		case R.id.delete:
			if(Length!=0){
				//����
				if (id == 1) {
					if (cursor != null && cursor.getCount() != 0) {
						sqldb.execSQL("delete from collect where question='"
								+ questionString + "'");
						//ɾ������ĳ��
						list.remove(Pointer);
						--Length;
						Next();
					} else {
						Toast.makeText(this, "��ľ������", Toast.LENGTH_SHORT)
								.show();
					}
				} 
				else if (id == 3) {
					if (cursor != null && cursor.getCount() != 0) {
						sqldb.execSQL("delete from wrong where question='"
								+ questionString + "'");

						list.remove(Pointer);
						--Length;
						Next();
					} else {
						Toast.makeText(this, "��ľ������", Toast.LENGTH_SHORT)
								.show();
					}
				}

			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void Next(){
		System.out.println("next");
		if(Length==0){
			//���ؿؼ�
			zhangjiequestion.setVisibility(View.INVISIBLE);
			btnA.setVisibility(View.INVISIBLE);
			btnB.setVisibility(View.INVISIBLE);
			btnC.setVisibility(View.INVISIBLE);
			btnD.setVisibility(View.INVISIBLE);
			zhangjiejiexi.setVisibility(View.INVISIBLE);
			finish();
		}
		else {
			if(Pointer==Length){
				--Pointer;
			}

			btnGrp.clearCheck();
			questionString=list.get(Pointer).get("question");
			zhangjiequestion.setText(questionString);
			btnA.setText(list.get(Pointer).get("A"));
			btnB.setText(list.get(Pointer).get("B"));
			btnC.setText(list.get(Pointer).get("C"));
			btnD.setText(list.get(Pointer).get("D"));
			btnA.setTextColor(Color.BLACK);
			btnB.setTextColor(Color.BLACK);
			btnC.setTextColor(Color.BLACK);
			btnD.setTextColor(Color.BLACK);
			btnA.setClickable(true);
			btnB.setClickable(true);
			btnC.setClickable(true);
			btnD.setClickable(true);
			zhangjiejiexi.setText("");
		}
		return;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			ScrollView scrollView=(ScrollView)getLayoutInflater()
					.inflate(R.layout.dialog_zhangjielianxi, null);
					AlertDialog.Builder aBuilder=new AlertDialog.Builder(this);
					aBuilder.setTitle("��ܰ��ʾ");
					aBuilder.setView(scrollView);
					aBuilder.setNegativeButton("ȡ��", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					aBuilder.setPositiveButton("ȷ��", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
						}
					});
					aBuilder.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
