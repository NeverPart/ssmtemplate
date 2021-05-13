package cn.ch.controller;

import cn.ch.entity.Customer;
import cn.ch.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 这是客户管理的Controller层
 *
 * @author ch
 * @date 2021/5/13 14:15
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    /**
     * 注入service层
     * 使用@Resource和@Autowired都可以实现Bean的自动注入
     */
    @Autowired
    private ICustomerService ICustomerService;

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 跳转到添加客户功能页面
     * @Date 2021/5/13 14:26
     * @Param
     **/
    @RequestMapping("/toSavePage")
    public String toSavePage() {

        return "page/save";
    }

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 跳转到客户列表页面
     * @Date 2021/5/13 14:25
     * @Param model:
     **/
    @RequestMapping(value = "/toListPage")
    public String toListPage(Model model) {

        return "redirect:findByPage.do";
    }

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 客户信息保存
     * @Date 2021/5/13 14:26
     * @Param customer:
     * @Param model:
     **/
    @RequestMapping(value = "/save")
    public String create(Customer customer, Model model) {

        try {
            ICustomerService.create(customer);
            model.addAttribute("message", "保存客户信息系成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "page/info";
    }

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 客户信息删除的方法
     * @Date 2021/5/13 14:27
     * @Param id:
     * @Param model:
     **/
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id, Model model) {

        try {
            ICustomerService.delete(id);
            model.addAttribute("message", "删除客户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "page/info";
    }


    /**
     * @return cn.ch.pojo.Customer
     * @Author ch
     * @Description 根据id查询客户信息方法
     * @Date 2021/5/13 14:27
     * @Param customer:
     **/
    @ResponseBody
    @RequestMapping(value = "/findById")
    public Customer findById(@RequestBody Customer customer) {

        Customer customer_info = ICustomerService.findById(customer.getId());
        if (customer_info != null) {
            return customer_info;
        } else {
            return null;
        }
    }

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 更新客户信息的方法
     * @Date 2021/5/13 14:27
     * @Param customer:
     * @Param model:
     **/
    @RequestMapping(value = "/update")
    public String update(Customer customer, Model model) {
        try {
            ICustomerService.update(customer);
            model.addAttribute("message", "更新客户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "page/info";
    }

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 分页查询
     * @Date 2021/5/13 14:28
     * @Param customer: 查询条件
     * @Param pageCode: 当前页
     * @Param pageSize: 每页显示的记录数
     * @Param model:
     **/
    @RequestMapping("/findByPage")
    public String findByPage(Customer customer,
                             @RequestParam(value = "pageCode", required = false, defaultValue = "1") int pageCode,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize,
                             Model model) {
        // 回显数据
        model.addAttribute("page", ICustomerService.findByPage(customer, pageCode, pageSize));
        return "page/list";
    }

}
