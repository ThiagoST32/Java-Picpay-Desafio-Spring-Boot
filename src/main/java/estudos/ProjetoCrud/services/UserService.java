package estudos.ProjetoCrud.services;

import estudos.ProjetoCrud.domain.User;
import estudos.ProjetoCrud.domain.UserType;
import estudos.ProjetoCrud.dto.UserDTO;
import estudos.ProjetoCrud.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepositories userRepositories;

    public void verifyTypeUser(User sender, BigDecimal tokens) throws Exception {
        if (sender.getUserType() == UserType.Receiver){
            throw new Exception ("O usuario não pode fazer transações");
        }if (sender.getTokens().compareTo(tokens) <= 0){
            throw  new Exception("O usuario não possui tokens suficientes");
        }
    }

    public User findByIdUser(Long id) throws Exception {
        return this.userRepositories.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado!!"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        System.out.println(data);
        return newUser;
    }

    public void saveUser(User user){
        this.userRepositories.save(user);
    }

    public List<User> getAllUsers(){
        System.out.println(this.userRepositories.findAll());
        return this.userRepositories.findAll();
    }

    public void deleteUser(){

    }

}
