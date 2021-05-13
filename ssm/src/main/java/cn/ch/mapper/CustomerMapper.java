package cn.ch.mapper;

import cn.ch.entity.Customer;
import com.github.pagehelper.Page;

/**
 * @author ch
 * @date 2021/5/13
 */
public interface CustomerMapper {

    void create(Customer customer);

    void delete(Long id);

    Customer findById(Long id);

    void update(Customer customer);

    Page<Customer> findByPage(Customer customer);

//    int selectCount();

//    List<Customer> findCondition(Map<String,Object> conMap);
}
