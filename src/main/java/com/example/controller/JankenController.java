package com.example.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.JankenForm;

@Controller
@RequestMapping("/janken")
public class JankenController {
	
	private Map<Integer, String> hitonoteMap;
	
	public JankenController() {
		hitonoteMap = new HashMap<>();
		hitonoteMap.put(1, "グー");
		hitonoteMap.put(2, "チョキ");
		hitonoteMap.put(3, "パー");
	
	}
	
	@ModelAttribute
	public JankenForm setUpForm() {
		return new JankenForm();
	}
	
	@RequestMapping("")
	public String jankennote(Model model) {
		
		List<String> hitonoteList = new ArrayList<>();
		hitonoteList.add("グー");
		hitonoteList.add("チョキ");
		hitonoteList.add("パー");
		
		model.addAttribute("hitonoteList", hitonoteList);

		model.addAttribute("hitonoteMap", hitonoteMap);

		return "janken2";
	}
	
	@RequestMapping("/battle")
	public String battle(@Validated JankenForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return jankennote(model);
		}
		
		double random = Math.random();
		int aitenomake1 = (form.getHitonote() + 1) % 3;
		int aitenomake2 = form.getHitonote() + 1;
		int aitenokachi1 = form.getHitonote() + 2;
		int aitenokachi2 = form.getHitonote() - 1;
		int aiko = form.getHitonote();
		
		if(random < 0.333333333) {
			if(form.getHitonote() == 3) {
				model.addAttribute("winMessage", "あなた：" + hitonoteMap.get(form.getHitonote()) + " 相手：" + hitonoteMap.get(aitenomake1) + "　勝ち");
			} else {
				model.addAttribute("winMessage", "あなた：" + hitonoteMap.get(form.getHitonote()) + " 相手：" + hitonoteMap.get(aitenomake2) + "　勝ち");
			}
		} else if (random < 0.6666666666) {
			if(form.getHitonote() == 1) {
				model.addAttribute("loseMessage", "あなた：" + hitonoteMap.get(form.getHitonote()) + " 相手：" + hitonoteMap.get(aitenokachi1) + "　負け");
			} else {
				model.addAttribute("loseMessage", "あなた：" + hitonoteMap.get(form.getHitonote()) + " 相手：" + hitonoteMap.get(aitenokachi2) + "　負け");
			}
		} else {
			model.addAttribute("drawMessage", "あなた：" + hitonoteMap.get(form.getHitonote()) + " 相手：" + hitonoteMap.get(aiko) + "　あいこ");
		}

		return jankennote(model);
	}
	
	@RequestMapping("/result")
	public String result() {
		return "janken2";
	}
}
