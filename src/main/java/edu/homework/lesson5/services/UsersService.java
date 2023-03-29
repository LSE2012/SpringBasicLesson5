package edu.homework.lesson5.services;

import edu.homework.lesson5.entity.Users;
import edu.homework.lesson5.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional (readOnly = true)
public class UsersService {
    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAll() {
       return usersRepository.findAll();
    }

    public Users findOne(int id) {
        Optional<Users> foundUser = usersRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(Users users) {
        usersRepository.save(users);
    }

    @Transactional
    public void update(int id, Users updateUsers) {
        updateUsers.setId(id);
        usersRepository.save(updateUsers);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

}
