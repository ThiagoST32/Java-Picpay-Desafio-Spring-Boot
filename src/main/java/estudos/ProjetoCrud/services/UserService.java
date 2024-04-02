package estudos.ProjetoCrud.services;

import estudos.ProjetoCrud.domain.User;
import estudos.ProjetoCrud.domain.UserType;
import estudos.ProjetoCrud.dto.UserDTO;
import estudos.ProjetoCrud.dto.UserUpdateDTO;
import estudos.ProjetoCrud.repositories.UserRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepositories userRepositories;

    public void verifyTypeUser(User sender, BigDecimal tokens) throws Exception {
        if (sender.getUserType() == UserType.Receiver) {
            throw new Exception("O usuario não pode fazer transações");
        }
        if (sender.getTokens().compareTo(tokens) <= 0) {
            throw new Exception("O usuario não possui tokens suficientes");
        }
    }

    public User findByIdUser(Long id) throws Exception {
        return this.userRepositories.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado!!"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        System.out.println(data);
        return newUser;
    }

    public void saveUser(User user) {
        this.userRepositories.save(user);
    }

    public List<User> getAllUsers() {
        System.out.println(this.userRepositories.findAll());
        return this.userRepositories.findAll();
    }

    @Transactional
    public void deleteUser(Long id) {
        try {
            this.userRepositories.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar o usuario!!");
        }

    }

    @Transactional
    public User updateUser(UserUpdateDTO userUpdateDTO, Long id) throws Exception {
        User newObjUser = findByIdUser(id);
        if (id == newObjUser.getId()){
            newObjUser.setUserType(userUpdateDTO.userType());
            newObjUser.setEmail(userUpdateDTO.email());
            newObjUser.setFirstName(userUpdateDTO.firstName());
            newObjUser.setLastName(userUpdateDTO.lastName());
            System.out.println(userUpdateDTO);
            return this.userRepositories.save(newObjUser);
        }throw new Exception("O id do usuario n existe");
    }

}
