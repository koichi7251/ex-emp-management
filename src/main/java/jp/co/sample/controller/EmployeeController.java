package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラ
 * 
 * @author koichi.nagata
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員リストを表示する.
	 * 
	 * @param model モデル
	 * @return リスト表示画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/**
	 * 扶養人数変更時にUpdateEmployeeFormをリクエストスコープに自動的に格納する.
	 * 
	 * @return UpdateEmployeeFormのインスタンス
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpDateEmployeeForm() {
		UpdateEmployeeForm upDateEmployeeForm = new UpdateEmployeeForm();
		return upDateEmployeeForm;
	}

	/**
	 * 従業員1人の情報を表示する.
	 * 
	 * @param id    ID
	 * @param model model
	 * @return 従業員個人情報
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		model.addAttribute("employee", employeeService.showDetail(Integer.parseInt(id)));
		return "employee/detail";
	}

	/**
	 * 扶養人数更新操作.
	 * 
	 * @param form  フォーム requestパラメータがはいっているフォーム
	 * @param model モデル
	 * @return 従業員一覧表示
	 */
	@RequestMapping("/update")
	public String update(String id, @Validated UpdateEmployeeForm form, 
			 			 BindingResult result,RedirectAttributes redirectAttributes,Model model) {
		if(result.hasErrors()) {
			return showDetail(id,model);
		}
		
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
}
