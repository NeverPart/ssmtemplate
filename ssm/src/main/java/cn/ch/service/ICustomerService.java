package cn.ch.service;

import cn.ch.entity.Customer;
import cn.ch.entity.PageBean;

/**
 * @author ch
 * @date 2021/5/13
 */
public interface ICustomerService extends BaseService<Customer> {

    /**
     * 分页查询
     * @param customer 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(Customer customer, int pageCode, int pageSize);

}
