package com.blog.ln.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ln.bean.MessageInfo;
import com.blog.ln.dao.MessageInfoMapper;
import com.blog.ln.service.MessageService;
import com.blog.ln.utils.Const;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageInfoMapper messmapper;
	
	@Override
	public List<MessageInfo> getList(MessageInfo info) {
		// TODO Auto-generated method stub
		return messmapper.getMessageList(info);
	}

	@Override
	public boolean update(MessageInfo info) {
		try {
			
			int count = messmapper.updateByPrimaryKeySelective(info);
			if(count>0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer messageId) {
		try {
			
			int count = messmapper.deleteByPrimaryKey(messageId);
			if(count>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MessageInfo> getMessagesList() {
		// TODO Auto-generated method stub
		return messmapper.getMessagesList();
	}

	@Override
	public boolean add(MessageInfo info) {
		try {
			if(info!=null) {
				info.setMessageTime(new Date());
				info.setMessageMark(Const.MESSAGE_MARK);
			}
			int count = messmapper.insertSelective(info);
			if(count>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
