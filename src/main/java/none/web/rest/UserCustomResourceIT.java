/*
package none.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import IntegrationTest;
import none.domain.UserCustom;
import none.repository.UserCustomRepository;
import none.service.dto.UserCustom;
import none.service.mapper.UserCustomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

*/
/**
 * Integration tests for the {@link UserCustomResource} REST controller.
 *//*

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserCustomResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_ROLES = "AAAAAAAAAA";
    private static final String UPDATED_ROLES = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHORITIES = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORITIES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_NOT_LOCKED = false;
    private static final Boolean UPDATED_IS_NOT_LOCKED = true;

    private static final Boolean DEFAULT_IS_ACTIVED = false;
    private static final Boolean UPDATED_IS_ACTIVED = true;

    private static final LocalDate DEFAULT_JOIN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOIN_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_JOIN_DATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_LAST_LOGIN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_LOGIN_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_LAST_LOGIN_DATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_LAST_LOGIN_DATE_DISPLAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_LOGIN_DATE_DISPLAY = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_LAST_LOGIN_DATE_DISPLAY = LocalDate.ofEpochDay(-1L);

    private static final String ENTITY_API_URL = "/api/user-customs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Autowired
    private UserCustomMapper userCustomMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserCustomMockMvc;

    private UserCustom userCustom;

    */
/**
 * Create an entity for this test.
 *
 * This is a static method, as tests for other entities might also need it,
 * if they test an entity which requires the current entity.
 *//*

    public static UserCustom createEntity(EntityManager em) {
        UserCustom userCustom = new UserCustom()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .username(DEFAULT_USERNAME)
            .email(DEFAULT_EMAIL)
            .password(DEFAULT_PASSWORD)
            .roles(DEFAULT_ROLES)
            .authorities(DEFAULT_AUTHORITIES)
            .isNotLocked(DEFAULT_IS_NOT_LOCKED)
            .isActived(DEFAULT_IS_ACTIVED)
            .joinDate(DEFAULT_JOIN_DATE)
            .lastLoginDate(DEFAULT_LAST_LOGIN_DATE)
            .lastLoginDateDisplay(DEFAULT_LAST_LOGIN_DATE_DISPLAY);
        return userCustom;
    }

    */
/**
 * Create an updated entity for this test.
 *
 * This is a static method, as tests for other entities might also need it,
 * if they test an entity which requires the current entity.
 *//*

    public static UserCustom createUpdatedEntity(EntityManager em) {
        UserCustom userCustom = new UserCustom()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .username(UPDATED_USERNAME)
            .email(UPDATED_EMAIL)
            .password(UPDATED_PASSWORD)
            .roles(UPDATED_ROLES)
            .authorities(UPDATED_AUTHORITIES)
            .isNotLocked(UPDATED_IS_NOT_LOCKED)
            .isActived(UPDATED_IS_ACTIVED)
            .joinDate(UPDATED_JOIN_DATE)
            .lastLoginDate(UPDATED_LAST_LOGIN_DATE)
            .lastLoginDateDisplay(UPDATED_LAST_LOGIN_DATE_DISPLAY);
        return userCustom;
    }

    @BeforeEach
    public void initTest() {
        userCustom = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUserCustoms() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList
        restUserCustomMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCustom.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].roles").value(hasItem(DEFAULT_ROLES)))
            .andExpect(jsonPath("$.[*].authorities").value(hasItem(DEFAULT_AUTHORITIES)))
            .andExpect(jsonPath("$.[*].isNotLocked").value(hasItem(DEFAULT_IS_NOT_LOCKED.booleanValue())))
            .andExpect(jsonPath("$.[*].isActived").value(hasItem(DEFAULT_IS_ACTIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].joinDate").value(hasItem(DEFAULT_JOIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastLoginDate").value(hasItem(DEFAULT_LAST_LOGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastLoginDateDisplay").value(hasItem(DEFAULT_LAST_LOGIN_DATE_DISPLAY.toString())));
    }

    @Test
    @Transactional
    void getUserCustom() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get the userCustom
        restUserCustomMockMvc
            .perform(get(ENTITY_API_URL_ID, userCustom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userCustom.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.roles").value(DEFAULT_ROLES))
            .andExpect(jsonPath("$.authorities").value(DEFAULT_AUTHORITIES))
            .andExpect(jsonPath("$.isNotLocked").value(DEFAULT_IS_NOT_LOCKED.booleanValue()))
            .andExpect(jsonPath("$.isActived").value(DEFAULT_IS_ACTIVED.booleanValue()))
            .andExpect(jsonPath("$.joinDate").value(DEFAULT_JOIN_DATE.toString()))
            .andExpect(jsonPath("$.lastLoginDate").value(DEFAULT_LAST_LOGIN_DATE.toString()))
            .andExpect(jsonPath("$.lastLoginDateDisplay").value(DEFAULT_LAST_LOGIN_DATE_DISPLAY.toString()));
    }

    @Test
    @Transactional
    void getUserCustomsByIdFiltering() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        Long id = userCustom.getId();

        defaultUserCustomShouldBeFound("id.equals=" + id);
        defaultUserCustomShouldNotBeFound("id.notEquals=" + id);

        defaultUserCustomShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultUserCustomShouldNotBeFound("id.greaterThan=" + id);

        defaultUserCustomShouldBeFound("id.lessThanOrEqual=" + id);
        defaultUserCustomShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllUserCustomsByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where firstName equals to DEFAULT_FIRST_NAME
        defaultUserCustomShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the userCustomList where firstName equals to UPDATED_FIRST_NAME
        defaultUserCustomShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultUserCustomShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the userCustomList where firstName equals to UPDATED_FIRST_NAME
        defaultUserCustomShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where firstName is not null
        defaultUserCustomShouldBeFound("firstName.specified=true");

        // Get all the userCustomList where firstName is null
        defaultUserCustomShouldNotBeFound("firstName.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where firstName contains DEFAULT_FIRST_NAME
        defaultUserCustomShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the userCustomList where firstName contains UPDATED_FIRST_NAME
        defaultUserCustomShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where firstName does not contain DEFAULT_FIRST_NAME
        defaultUserCustomShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the userCustomList where firstName does not contain UPDATED_FIRST_NAME
        defaultUserCustomShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastName equals to DEFAULT_LAST_NAME
        defaultUserCustomShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the userCustomList where lastName equals to UPDATED_LAST_NAME
        defaultUserCustomShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultUserCustomShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the userCustomList where lastName equals to UPDATED_LAST_NAME
        defaultUserCustomShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastName is not null
        defaultUserCustomShouldBeFound("lastName.specified=true");

        // Get all the userCustomList where lastName is null
        defaultUserCustomShouldNotBeFound("lastName.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastNameContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastName contains DEFAULT_LAST_NAME
        defaultUserCustomShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the userCustomList where lastName contains UPDATED_LAST_NAME
        defaultUserCustomShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastName does not contain DEFAULT_LAST_NAME
        defaultUserCustomShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the userCustomList where lastName does not contain UPDATED_LAST_NAME
        defaultUserCustomShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByUsernameIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where username equals to DEFAULT_USERNAME
        defaultUserCustomShouldBeFound("username.equals=" + DEFAULT_USERNAME);

        // Get all the userCustomList where username equals to UPDATED_USERNAME
        defaultUserCustomShouldNotBeFound("username.equals=" + UPDATED_USERNAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByUsernameIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where username in DEFAULT_USERNAME or UPDATED_USERNAME
        defaultUserCustomShouldBeFound("username.in=" + DEFAULT_USERNAME + "," + UPDATED_USERNAME);

        // Get all the userCustomList where username equals to UPDATED_USERNAME
        defaultUserCustomShouldNotBeFound("username.in=" + UPDATED_USERNAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByUsernameIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where username is not null
        defaultUserCustomShouldBeFound("username.specified=true");

        // Get all the userCustomList where username is null
        defaultUserCustomShouldNotBeFound("username.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByUsernameContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where username contains DEFAULT_USERNAME
        defaultUserCustomShouldBeFound("username.contains=" + DEFAULT_USERNAME);

        // Get all the userCustomList where username contains UPDATED_USERNAME
        defaultUserCustomShouldNotBeFound("username.contains=" + UPDATED_USERNAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByUsernameNotContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where username does not contain DEFAULT_USERNAME
        defaultUserCustomShouldNotBeFound("username.doesNotContain=" + DEFAULT_USERNAME);

        // Get all the userCustomList where username does not contain UPDATED_USERNAME
        defaultUserCustomShouldBeFound("username.doesNotContain=" + UPDATED_USERNAME);
    }

    @Test
    @Transactional
    void getAllUserCustomsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where email equals to DEFAULT_EMAIL
        defaultUserCustomShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the userCustomList where email equals to UPDATED_EMAIL
        defaultUserCustomShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllUserCustomsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultUserCustomShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the userCustomList where email equals to UPDATED_EMAIL
        defaultUserCustomShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllUserCustomsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where email is not null
        defaultUserCustomShouldBeFound("email.specified=true");

        // Get all the userCustomList where email is null
        defaultUserCustomShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByEmailContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where email contains DEFAULT_EMAIL
        defaultUserCustomShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the userCustomList where email contains UPDATED_EMAIL
        defaultUserCustomShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllUserCustomsByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where email does not contain DEFAULT_EMAIL
        defaultUserCustomShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the userCustomList where email does not contain UPDATED_EMAIL
        defaultUserCustomShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllUserCustomsByPasswordIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where password equals to DEFAULT_PASSWORD
        defaultUserCustomShouldBeFound("password.equals=" + DEFAULT_PASSWORD);

        // Get all the userCustomList where password equals to UPDATED_PASSWORD
        defaultUserCustomShouldNotBeFound("password.equals=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllUserCustomsByPasswordIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where password in DEFAULT_PASSWORD or UPDATED_PASSWORD
        defaultUserCustomShouldBeFound("password.in=" + DEFAULT_PASSWORD + "," + UPDATED_PASSWORD);

        // Get all the userCustomList where password equals to UPDATED_PASSWORD
        defaultUserCustomShouldNotBeFound("password.in=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllUserCustomsByPasswordIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where password is not null
        defaultUserCustomShouldBeFound("password.specified=true");

        // Get all the userCustomList where password is null
        defaultUserCustomShouldNotBeFound("password.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByPasswordContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where password contains DEFAULT_PASSWORD
        defaultUserCustomShouldBeFound("password.contains=" + DEFAULT_PASSWORD);

        // Get all the userCustomList where password contains UPDATED_PASSWORD
        defaultUserCustomShouldNotBeFound("password.contains=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllUserCustomsByPasswordNotContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where password does not contain DEFAULT_PASSWORD
        defaultUserCustomShouldNotBeFound("password.doesNotContain=" + DEFAULT_PASSWORD);

        // Get all the userCustomList where password does not contain UPDATED_PASSWORD
        defaultUserCustomShouldBeFound("password.doesNotContain=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllUserCustomsByRolesIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where roles equals to DEFAULT_ROLES
        defaultUserCustomShouldBeFound("roles.equals=" + DEFAULT_ROLES);

        // Get all the userCustomList where roles equals to UPDATED_ROLES
        defaultUserCustomShouldNotBeFound("roles.equals=" + UPDATED_ROLES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByRolesIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where roles in DEFAULT_ROLES or UPDATED_ROLES
        defaultUserCustomShouldBeFound("roles.in=" + DEFAULT_ROLES + "," + UPDATED_ROLES);

        // Get all the userCustomList where roles equals to UPDATED_ROLES
        defaultUserCustomShouldNotBeFound("roles.in=" + UPDATED_ROLES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByRolesIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where roles is not null
        defaultUserCustomShouldBeFound("roles.specified=true");

        // Get all the userCustomList where roles is null
        defaultUserCustomShouldNotBeFound("roles.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByRolesContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where roles contains DEFAULT_ROLES
        defaultUserCustomShouldBeFound("roles.contains=" + DEFAULT_ROLES);

        // Get all the userCustomList where roles contains UPDATED_ROLES
        defaultUserCustomShouldNotBeFound("roles.contains=" + UPDATED_ROLES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByRolesNotContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where roles does not contain DEFAULT_ROLES
        defaultUserCustomShouldNotBeFound("roles.doesNotContain=" + DEFAULT_ROLES);

        // Get all the userCustomList where roles does not contain UPDATED_ROLES
        defaultUserCustomShouldBeFound("roles.doesNotContain=" + UPDATED_ROLES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByAuthoritiesIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where authorities equals to DEFAULT_AUTHORITIES
        defaultUserCustomShouldBeFound("authorities.equals=" + DEFAULT_AUTHORITIES);

        // Get all the userCustomList where authorities equals to UPDATED_AUTHORITIES
        defaultUserCustomShouldNotBeFound("authorities.equals=" + UPDATED_AUTHORITIES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByAuthoritiesIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where authorities in DEFAULT_AUTHORITIES or UPDATED_AUTHORITIES
        defaultUserCustomShouldBeFound("authorities.in=" + DEFAULT_AUTHORITIES + "," + UPDATED_AUTHORITIES);

        // Get all the userCustomList where authorities equals to UPDATED_AUTHORITIES
        defaultUserCustomShouldNotBeFound("authorities.in=" + UPDATED_AUTHORITIES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByAuthoritiesIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where authorities is not null
        defaultUserCustomShouldBeFound("authorities.specified=true");

        // Get all the userCustomList where authorities is null
        defaultUserCustomShouldNotBeFound("authorities.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByAuthoritiesContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where authorities contains DEFAULT_AUTHORITIES
        defaultUserCustomShouldBeFound("authorities.contains=" + DEFAULT_AUTHORITIES);

        // Get all the userCustomList where authorities contains UPDATED_AUTHORITIES
        defaultUserCustomShouldNotBeFound("authorities.contains=" + UPDATED_AUTHORITIES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByAuthoritiesNotContainsSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where authorities does not contain DEFAULT_AUTHORITIES
        defaultUserCustomShouldNotBeFound("authorities.doesNotContain=" + DEFAULT_AUTHORITIES);

        // Get all the userCustomList where authorities does not contain UPDATED_AUTHORITIES
        defaultUserCustomShouldBeFound("authorities.doesNotContain=" + UPDATED_AUTHORITIES);
    }

    @Test
    @Transactional
    void getAllUserCustomsByIsNotLockedIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where isNotLocked equals to DEFAULT_IS_NOT_LOCKED
        defaultUserCustomShouldBeFound("isNotLocked.equals=" + DEFAULT_IS_NOT_LOCKED);

        // Get all the userCustomList where isNotLocked equals to UPDATED_IS_NOT_LOCKED
        defaultUserCustomShouldNotBeFound("isNotLocked.equals=" + UPDATED_IS_NOT_LOCKED);
    }

    @Test
    @Transactional
    void getAllUserCustomsByIsNotLockedIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where isNotLocked in DEFAULT_IS_NOT_LOCKED or UPDATED_IS_NOT_LOCKED
        defaultUserCustomShouldBeFound("isNotLocked.in=" + DEFAULT_IS_NOT_LOCKED + "," + UPDATED_IS_NOT_LOCKED);

        // Get all the userCustomList where isNotLocked equals to UPDATED_IS_NOT_LOCKED
        defaultUserCustomShouldNotBeFound("isNotLocked.in=" + UPDATED_IS_NOT_LOCKED);
    }

    @Test
    @Transactional
    void getAllUserCustomsByIsNotLockedIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where isNotLocked is not null
        defaultUserCustomShouldBeFound("isNotLocked.specified=true");

        // Get all the userCustomList where isNotLocked is null
        defaultUserCustomShouldNotBeFound("isNotLocked.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByIsActivedIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where isActived equals to DEFAULT_IS_ACTIVED
        defaultUserCustomShouldBeFound("isActived.equals=" + DEFAULT_IS_ACTIVED);

        // Get all the userCustomList where isActived equals to UPDATED_IS_ACTIVED
        defaultUserCustomShouldNotBeFound("isActived.equals=" + UPDATED_IS_ACTIVED);
    }

    @Test
    @Transactional
    void getAllUserCustomsByIsActivedIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where isActived in DEFAULT_IS_ACTIVED or UPDATED_IS_ACTIVED
        defaultUserCustomShouldBeFound("isActived.in=" + DEFAULT_IS_ACTIVED + "," + UPDATED_IS_ACTIVED);

        // Get all the userCustomList where isActived equals to UPDATED_IS_ACTIVED
        defaultUserCustomShouldNotBeFound("isActived.in=" + UPDATED_IS_ACTIVED);
    }

    @Test
    @Transactional
    void getAllUserCustomsByIsActivedIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where isActived is not null
        defaultUserCustomShouldBeFound("isActived.specified=true");

        // Get all the userCustomList where isActived is null
        defaultUserCustomShouldNotBeFound("isActived.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByJoinDateIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where joinDate equals to DEFAULT_JOIN_DATE
        defaultUserCustomShouldBeFound("joinDate.equals=" + DEFAULT_JOIN_DATE);

        // Get all the userCustomList where joinDate equals to UPDATED_JOIN_DATE
        defaultUserCustomShouldNotBeFound("joinDate.equals=" + UPDATED_JOIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByJoinDateIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where joinDate in DEFAULT_JOIN_DATE or UPDATED_JOIN_DATE
        defaultUserCustomShouldBeFound("joinDate.in=" + DEFAULT_JOIN_DATE + "," + UPDATED_JOIN_DATE);

        // Get all the userCustomList where joinDate equals to UPDATED_JOIN_DATE
        defaultUserCustomShouldNotBeFound("joinDate.in=" + UPDATED_JOIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByJoinDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where joinDate is not null
        defaultUserCustomShouldBeFound("joinDate.specified=true");

        // Get all the userCustomList where joinDate is null
        defaultUserCustomShouldNotBeFound("joinDate.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByJoinDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where joinDate is greater than or equal to DEFAULT_JOIN_DATE
        defaultUserCustomShouldBeFound("joinDate.greaterThanOrEqual=" + DEFAULT_JOIN_DATE);

        // Get all the userCustomList where joinDate is greater than or equal to UPDATED_JOIN_DATE
        defaultUserCustomShouldNotBeFound("joinDate.greaterThanOrEqual=" + UPDATED_JOIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByJoinDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where joinDate is less than or equal to DEFAULT_JOIN_DATE
        defaultUserCustomShouldBeFound("joinDate.lessThanOrEqual=" + DEFAULT_JOIN_DATE);

        // Get all the userCustomList where joinDate is less than or equal to SMALLER_JOIN_DATE
        defaultUserCustomShouldNotBeFound("joinDate.lessThanOrEqual=" + SMALLER_JOIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByJoinDateIsLessThanSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where joinDate is less than DEFAULT_JOIN_DATE
        defaultUserCustomShouldNotBeFound("joinDate.lessThan=" + DEFAULT_JOIN_DATE);

        // Get all the userCustomList where joinDate is less than UPDATED_JOIN_DATE
        defaultUserCustomShouldBeFound("joinDate.lessThan=" + UPDATED_JOIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByJoinDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where joinDate is greater than DEFAULT_JOIN_DATE
        defaultUserCustomShouldNotBeFound("joinDate.greaterThan=" + DEFAULT_JOIN_DATE);

        // Get all the userCustomList where joinDate is greater than SMALLER_JOIN_DATE
        defaultUserCustomShouldBeFound("joinDate.greaterThan=" + SMALLER_JOIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDate equals to DEFAULT_LAST_LOGIN_DATE
        defaultUserCustomShouldBeFound("lastLoginDate.equals=" + DEFAULT_LAST_LOGIN_DATE);

        // Get all the userCustomList where lastLoginDate equals to UPDATED_LAST_LOGIN_DATE
        defaultUserCustomShouldNotBeFound("lastLoginDate.equals=" + UPDATED_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDate in DEFAULT_LAST_LOGIN_DATE or UPDATED_LAST_LOGIN_DATE
        defaultUserCustomShouldBeFound("lastLoginDate.in=" + DEFAULT_LAST_LOGIN_DATE + "," + UPDATED_LAST_LOGIN_DATE);

        // Get all the userCustomList where lastLoginDate equals to UPDATED_LAST_LOGIN_DATE
        defaultUserCustomShouldNotBeFound("lastLoginDate.in=" + UPDATED_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDate is not null
        defaultUserCustomShouldBeFound("lastLoginDate.specified=true");

        // Get all the userCustomList where lastLoginDate is null
        defaultUserCustomShouldNotBeFound("lastLoginDate.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDate is greater than or equal to DEFAULT_LAST_LOGIN_DATE
        defaultUserCustomShouldBeFound("lastLoginDate.greaterThanOrEqual=" + DEFAULT_LAST_LOGIN_DATE);

        // Get all the userCustomList where lastLoginDate is greater than or equal to UPDATED_LAST_LOGIN_DATE
        defaultUserCustomShouldNotBeFound("lastLoginDate.greaterThanOrEqual=" + UPDATED_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDate is less than or equal to DEFAULT_LAST_LOGIN_DATE
        defaultUserCustomShouldBeFound("lastLoginDate.lessThanOrEqual=" + DEFAULT_LAST_LOGIN_DATE);

        // Get all the userCustomList where lastLoginDate is less than or equal to SMALLER_LAST_LOGIN_DATE
        defaultUserCustomShouldNotBeFound("lastLoginDate.lessThanOrEqual=" + SMALLER_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateIsLessThanSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDate is less than DEFAULT_LAST_LOGIN_DATE
        defaultUserCustomShouldNotBeFound("lastLoginDate.lessThan=" + DEFAULT_LAST_LOGIN_DATE);

        // Get all the userCustomList where lastLoginDate is less than UPDATED_LAST_LOGIN_DATE
        defaultUserCustomShouldBeFound("lastLoginDate.lessThan=" + UPDATED_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDate is greater than DEFAULT_LAST_LOGIN_DATE
        defaultUserCustomShouldNotBeFound("lastLoginDate.greaterThan=" + DEFAULT_LAST_LOGIN_DATE);

        // Get all the userCustomList where lastLoginDate is greater than SMALLER_LAST_LOGIN_DATE
        defaultUserCustomShouldBeFound("lastLoginDate.greaterThan=" + SMALLER_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateDisplayIsEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDateDisplay equals to DEFAULT_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldBeFound("lastLoginDateDisplay.equals=" + DEFAULT_LAST_LOGIN_DATE_DISPLAY);

        // Get all the userCustomList where lastLoginDateDisplay equals to UPDATED_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldNotBeFound("lastLoginDateDisplay.equals=" + UPDATED_LAST_LOGIN_DATE_DISPLAY);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateDisplayIsInShouldWork() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDateDisplay in DEFAULT_LAST_LOGIN_DATE_DISPLAY or UPDATED_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldBeFound(
            "lastLoginDateDisplay.in=" + DEFAULT_LAST_LOGIN_DATE_DISPLAY + "," + UPDATED_LAST_LOGIN_DATE_DISPLAY
        );

        // Get all the userCustomList where lastLoginDateDisplay equals to UPDATED_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldNotBeFound("lastLoginDateDisplay.in=" + UPDATED_LAST_LOGIN_DATE_DISPLAY);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateDisplayIsNullOrNotNull() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDateDisplay is not null
        defaultUserCustomShouldBeFound("lastLoginDateDisplay.specified=true");

        // Get all the userCustomList where lastLoginDateDisplay is null
        defaultUserCustomShouldNotBeFound("lastLoginDateDisplay.specified=false");
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateDisplayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDateDisplay is greater than or equal to DEFAULT_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldBeFound("lastLoginDateDisplay.greaterThanOrEqual=" + DEFAULT_LAST_LOGIN_DATE_DISPLAY);

        // Get all the userCustomList where lastLoginDateDisplay is greater than or equal to UPDATED_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldNotBeFound("lastLoginDateDisplay.greaterThanOrEqual=" + UPDATED_LAST_LOGIN_DATE_DISPLAY);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateDisplayIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDateDisplay is less than or equal to DEFAULT_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldBeFound("lastLoginDateDisplay.lessThanOrEqual=" + DEFAULT_LAST_LOGIN_DATE_DISPLAY);

        // Get all the userCustomList where lastLoginDateDisplay is less than or equal to SMALLER_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldNotBeFound("lastLoginDateDisplay.lessThanOrEqual=" + SMALLER_LAST_LOGIN_DATE_DISPLAY);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateDisplayIsLessThanSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDateDisplay is less than DEFAULT_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldNotBeFound("lastLoginDateDisplay.lessThan=" + DEFAULT_LAST_LOGIN_DATE_DISPLAY);

        // Get all the userCustomList where lastLoginDateDisplay is less than UPDATED_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldBeFound("lastLoginDateDisplay.lessThan=" + UPDATED_LAST_LOGIN_DATE_DISPLAY);
    }

    @Test
    @Transactional
    void getAllUserCustomsByLastLoginDateDisplayIsGreaterThanSomething() throws Exception {
        // Initialize the database
        userCustomRepository.saveAndFlush(userCustom);

        // Get all the userCustomList where lastLoginDateDisplay is greater than DEFAULT_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldNotBeFound("lastLoginDateDisplay.greaterThan=" + DEFAULT_LAST_LOGIN_DATE_DISPLAY);

        // Get all the userCustomList where lastLoginDateDisplay is greater than SMALLER_LAST_LOGIN_DATE_DISPLAY
        defaultUserCustomShouldBeFound("lastLoginDateDisplay.greaterThan=" + SMALLER_LAST_LOGIN_DATE_DISPLAY);
    }

    */
/**
 * Executes the search, and checks that the default entity is returned.
 *//*

    private void defaultUserCustomShouldBeFound(String filter) throws Exception {
        restUserCustomMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCustom.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].roles").value(hasItem(DEFAULT_ROLES)))
            .andExpect(jsonPath("$.[*].authorities").value(hasItem(DEFAULT_AUTHORITIES)))
            .andExpect(jsonPath("$.[*].isNotLocked").value(hasItem(DEFAULT_IS_NOT_LOCKED.booleanValue())))
            .andExpect(jsonPath("$.[*].isActived").value(hasItem(DEFAULT_IS_ACTIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].joinDate").value(hasItem(DEFAULT_JOIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastLoginDate").value(hasItem(DEFAULT_LAST_LOGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastLoginDateDisplay").value(hasItem(DEFAULT_LAST_LOGIN_DATE_DISPLAY.toString())));

        // Check, that the count call also returns 1
        restUserCustomMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    */
/**
 * Executes the search, and checks that the default entity is not returned.
 *//*

    private void defaultUserCustomShouldNotBeFound(String filter) throws Exception {
        restUserCustomMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUserCustomMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingUserCustom() throws Exception {
        // Get the userCustom
        restUserCustomMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
*/
