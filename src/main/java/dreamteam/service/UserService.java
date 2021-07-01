package dreamteam.service;
import dreamteam.dao.UserDAO;
import dreamteam.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User saveUser(User user) {

        return userDAO.saveUser(user);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(Integer id) {
        return userDAO.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
