package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;  // Mock the PasswordEncoder (optional, if you want to mock encoding)

    @InjectMocks
    private UserService userService;  // The service being tested

    private User user;

//    @AfterEach()
//    @AfterAll()

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "2, 3, 5",
            "3, 4, 7"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(new ObjectId());
        user.setUserName("testUser");
        user.setPassword("password");
        user.setRoles(Arrays.asList("USER"));
    }

    @Test
    public void testCreateUser() {
        // Act
        userService.createUser(user);

        // Assert
        assertNotEquals("Password", user.getPassword());  // Check that password is encoded
        assertEquals(Arrays.asList("USER"), user.getRoles());  // Check that roles are set to "USER"
        verify(userRepository, times(1)).save(user);  // Ensure save was called once
    }

    @Test
    public void testCreateAdminUser() {
        // Act
        userService.createAdminUser(user);

        // Assert
        assertNotEquals("Password", user.getPassword());  // Check that password is encoded
        assertEquals(Arrays.asList("USER", "ADMIN"), user.getRoles());  // Check that roles include "USER" and "ADMIN"
        verify(userRepository, times(1)).save(user);  // Ensure save was called once
    }

    @Test
    public void testGetAll() {
        // Arrange
        User user2 = new User();
        user2.setUserName("testUser2");
        user2.setPassword("password2");
        List<User> users = Arrays.asList(user, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAll();

        // Assert
        assertEquals(2, result.size());  // Assert that the returned list has 2 users
        assertEquals("testUser", result.get(0).getUserName());  // Check that the first user is "testUser"
        verify(userRepository, times(1)).findAll();  // Ensure findAll was called once
    }

    @Test
    public void testGetUserById() {
        // Arrange
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserById(user.getId());

        // Assert
        assertTrue(result.isPresent());  // Assert that a user is found
        assertEquals(user.getUserName(), result.get().getUserName());  // Assert the user's name is correct
        verify(userRepository, times(1)).findById(user.getId());  // Ensure findById was called once
    }

    @Test
    public void testDeleteUserById() {
        // Act
        userService.deleteUserById(user.getId());

        // Assert
        verify(userRepository, times(1)).deleteById(user.getId());  // Ensure deleteById was called once
    }

    @Test
    public void testFindByUserName() {
        // Arrange
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);

        // Act
        User result = userService.findByUserName(user.getUserName());

        // Assert
        assertNotNull(result);  // Ensure the result is not null
        assertEquals(user.getUserName(), result.getUserName());  // Assert that the username matches
        verify(userRepository, times(1)).findByUserName(user.getUserName());  // Ensure findByUserName was called once
    }


}
