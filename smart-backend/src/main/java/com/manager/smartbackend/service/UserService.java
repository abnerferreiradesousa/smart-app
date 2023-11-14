package com.manager.smartbackend.service;

import com.manager.smartbackend.domain.dto.UserDto;
import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.domain.repository.UserRepository;
import com.manager.smartbackend.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String userId) {
        User user = this.userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new NotFoundException("User not found!"));
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                ""
        ).toEntity();
    }

    public User create(User userToCreate) {
        String hashedPassword = new BCryptPasswordEncoder().encode(userToCreate.getPassword());
        userToCreate.setPassword(hashedPassword);
        return this.userRepository.save(userToCreate);
    }

    public User update(User userToUpdate, String userId) {
        User userExists = this.getUserById(userId);

        userExists.setEmail(userToUpdate.getEmail());
        userExists.setName(userToUpdate.getName());
        userExists.setPassword(userToUpdate.getPassword());

        return new UserDto(
                userExists.getId(),
                userExists.getName(),
                userExists.getEmail(),
                userExists.getPassword(),
                ""
        ).toEntity();
    }

    public void remove(String userId) {
        this.userRepository.deleteById(Long.valueOf(userId));
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param email the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email);
    }
}
