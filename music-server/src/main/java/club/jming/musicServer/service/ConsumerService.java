package club.jming.musicServer.service;

import club.jming.musicServer.domain.Consumer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ConsumerService extends IService<Consumer> {

    boolean addUser(Consumer consumer);

    boolean updateUserMsg(Consumer consumer);

    boolean updateUserAvator(Consumer consumer);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean deleteUser(Integer id);

    List<Consumer> allUser();

    List<Consumer> userOfId(Integer id);

    List<Consumer> loginStatus(String username);

}
