package com.fzw.education.adapter;

import java.util.ArrayList;
import java.util.List;

import com.fzw.education.R;
import com.fzw.education.R.id;
import com.fzw.education.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter{

	private List<String> group;
	private List<List<String>> child;
	private Context context;
	public ExpandableAdapter(Context context){
		super();
		this.context=context;
		initializeData();
	}
	private void initializeData(){
    	group=new ArrayList<String>();
    	child=new ArrayList<List<String>>();
    	addInfo("����ѧ",new String[]{"��һ��  ���������ѧ","�ڶ���  ��������ᷢչ","������  ��������˷�չ",
    			"������  ����Ŀ��","������  ѧ�����ʦ","������  �γ�","������  ��ѧ��","�ڰ��� ��ѧ��","�ھ��� ����","��ʮ�� �༶����"});
    	addInfo("��������ѧ",new String[]{"��һ�� ��������ѧ����","�ڶ���  ��ѧ��������չ�����","������  ѧϰ�Ļ�������",
    			"������  ѧϰ����","������  ѧϰ��Ǩ��","������ ֪ʶ��ѧϰ","������ ���ܵ��γ�","�ڰ��� ѧϰ�Ĳ���"
    			,"�ھ��� �������봴����","��ʮ�� ̬����Ʒ�µ��γ�","��ʮһ�� ����������","��ʮ���� ��ѧ���",
    			"��ʮ���� ���ù���","��ʮ���� ��ѧ�����������","��ʮ���� ��ʦ���� "});
    }
    private void addInfo(String g,String[] c){
    	group.add(g);
    	List<String> childitem=new ArrayList<String>();
    	for(int i=0;i<c.length;i++){
    		childitem.add(c[i]);
    	}
    	child.add(childitem);
    }
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return group.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return child.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return group.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return child.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout relativeLayout1=(RelativeLayout)layoutInflater.inflate(R.layout.jiaoyugroup, null);
		TextView textView=(TextView)relativeLayout1.findViewById(R.id.jiaoyugroup_buju);
		textView.setText(group.get(groupPosition));
		
		return relativeLayout1;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater=(LayoutInflater)context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout relativeLayout2=(RelativeLayout)layoutInflater.inflate(R.layout.jiaoyuchild, null);
		TextView textView=(TextView)relativeLayout2.findViewById(R.id.jiaoyuchild_buju);
		textView.setText(child.get(groupPosition).get(childPosition));
		
		return relativeLayout2;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	

}