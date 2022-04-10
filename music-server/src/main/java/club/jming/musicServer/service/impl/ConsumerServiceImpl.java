package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.ConsumerMapper;
import club.jming.musicServer.domain.Consumer;
import club.jming.musicServer.service.ConsumerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer>
        implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 新增用户
     */
    @Override
    public boolean addUser(Consumer consumer) {
//        return consumerMapper.insertSelective(consumer) > 0;
        return this.save(consumer);
    }

    @Override
    public boolean updateUserMsg(Consumer consumer) {
//        return consumerMapper.updateUserMsg(consumer) > 0;
        return this.updateById(consumer);
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {
//        return consumerMapper.updateUserAvator(consumer) > 0;
        return this.updateById(consumer);
    }

    @Override
    public boolean existUser(String username) {
//        return consumerMapper.existUsername(username) > 0;
        QueryWrapper<Consumer> wrapper = new QueryWrapper<Consumer>();
        wrapper.eq("consumer_username",username);
        return this.count(wrapper) > 0;
    }

    @Override
    public boolean veritypasswd(String username, String password) {

//        return consumerMapper.verifyPassword(username, password) > 0;
        QueryWrapper<Consumer> wrapper = new QueryWrapper<Consumer>();
        wrapper.eq("consumer_username",username).eq("consumer_password",password);
        return this.count(wrapper) > 0;
    }

    //    删除用户
    @Override
    public boolean deleteUser(Integer id) {
//        return consumerMapper.deleteUser(id) > 0;
        return this.removeById(id);
    }

    @Override
    public List<Consumer> allUser() {
//        return consumerMapper.allUser();
        return this.list();
    }

    @Override
    public List<Consumer> userOfId(Integer id) {

//        return consumerMapper.userOfId(id);
        return this.list(new QueryWrapper<Consumer>().eq("consumer_id",id));
    }

    @Override
    public List<Consumer> loginStatus(String username) {

//        return consumerMapper.loginStatus(username);
        return this.list(new QueryWrapper<Consumer>().eq("consumer_username",username));
    }
}
