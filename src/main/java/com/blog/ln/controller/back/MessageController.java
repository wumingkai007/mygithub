package com.blog.ln.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.MessageInfo;
import com.blog.ln.service.MessageService;

/**
 * 留言管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/message/")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping("list")
	public String list(MessageInfo info,Model model) {
		
		List<MessageInfo> melist = messageService.getList(info);
		model.addAttribute("melist", melist);
		model.addAttribute("meinfo", info);
		
		return "back/message/message_list";
	}
	
	@RequestMapping("update")
	public String update(MessageInfo info,Model model) {
		
		//修改状态
		messageService.update(info);
		
		List<MessageInfo> melist = messageService.getList(info);
		model.addAttribute("melist", melist);
		
		return "back/message/message_list";
	}
	
	
	/**
	 * 根据编号删除文章信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id,Model model) {
		boolean mark = messageService.delete(id);
		if(mark) {
			model.addAttribute("info", "删除留言成功");
		}else {
			model.addAttribute("info", "删除留言失败");
		}
		return "back/message/message_info";
	}
}
