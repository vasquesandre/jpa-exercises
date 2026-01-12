package service;

import domain.User;
import infra.DatabaseCleaner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void shouldCreateUser() {
        UserService userService = new UserService();

        User user = userService.create(
                new User("Andre", "andre@email.com")
        );

        assertNotNull(user.getId());
        assertEquals("Andre", user.getUsername());
        assertEquals("andre@email.com", user.getEmail());
    }

    @Test
    void shouldGetUserById() {
        UserService userService = new UserService();

        User created = userService.create(
                new User("Maria", "maria@email.com")
        );

        User found = userService.get(created.getId());

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
    }

    @Test
    void shouldReturnNullWhenUserDoesNotExist() {
        UserService userService = new UserService();

        User user = userService.get(9999L);

        assertNull(user);
    }

    @Test
    void shouldUpdateUserEmail() {
        UserService userService = new UserService();

        User user = userService.create(
                new User("Joao", "joao@email.com")
        );

        User updated = userService.updateEmailById(
                user.getId(),
                "joao.novo@email.com"
        );

        assertEquals("joao.novo@email.com", updated.getEmail());
    }

    @Test
    void shouldGetUsersWithLimitAndOffset() {
        UserService userService = new UserService();

        userService.create(new User("U1", "u1@email.com"));
        userService.create(new User("U2", "u2@email.com"));
        userService.create(new User("U3", "u3@email.com"));

        List<User> users = userService.get(2, 0);

        assertEquals(2, users.size());
    }

    @Test
    void shouldDeleteUser() {
        UserService userService = new UserService();

        User user = userService.create(
                new User("Carlos", "carlos@email.com")
        );

        userService.delete(user.getId());

        User deleted = userService.get(user.getId());
        assertNull(deleted);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingUser() {
        UserService userService = new UserService();

        assertThrows(RuntimeException.class, () ->
                userService.delete(9999L)
        );
    }

    @AfterEach
    void cleanDatabase() {
        DatabaseCleaner.clean();
    }

}