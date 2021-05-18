//package com.speedhome;
//
//import com.truecom.RayApp;
//import com.truecom.api.service.UserApiService;
//import com.truecom.domain.User;
//import com.truecom.ray.common.dto.user.UserDTO;
//import com.truecom.repository.UserRepository;
//import com.truecom.security.AuthoritiesConstants;
//import com.truecom.service.MailService;
//import com.truecom.service.UserService;
//import com.truecom.service.mapper.UserMapper;
//import com.truecom.web.rest.errors.ExceptionTranslator;
//import com.truecom.web.rest.vm.ManagedUserVM;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cache.CacheManager;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.time.Instant;
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.containsInAnyOrder;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * Test class for the UserResource REST controller.
// *
// * @see UserResource
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RayApp.class)
//public class PropertyResourceTest {
//
//    private static final String DEFAULT_LOGIN = "johndoe";
//    private static final String UPDATED_LOGIN = "jhipster";
//
//    private static final UUID DEFAULT_ID = UUID.randomUUID();
//
//    private static final String DEFAULT_PASSWORD = "passjohndoe";
//    private static final String UPDATED_PASSWORD = "passjhipster";
//
//    private static final String DEFAULT_EMAIL = "johndoe@localhost";
//    private static final String UPDATED_EMAIL = "jhipster@localhost";
//
//    private static final String DEFAULT_FIRSTNAME = "john";
//    private static final String UPDATED_FIRSTNAME = "jhipsterFirstName";
//
//    private static final String DEFAULT_LASTNAME = "doe";
//    private static final String UPDATED_LASTNAME = "jhipsterLastName";
//
//    private static final String DEFAULT_IMAGEURL = "http://placehold.it/50x50";
//    private static final String UPDATED_IMAGEURL = "http://placehold.it/40x40";
//
//    private static final String DEFAULT_LANGKEY = "en";
//    private static final String UPDATED_LANGKEY = "fr";
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private MailService mailService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserApiService userApiService;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    private MockMvc restUserMockMvc;
//
//    private User user;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).clear();
//        cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).clear();
//        UserResource userResource = new UserResource(userService, userRepository, mailService, userApiService);
//        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setMessageConverters(jacksonMessageConverter)
//            .build();
//    }
//
//    /**
//     * Create a User.
//     * <p>
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which has a required relationship to the User entity.
//     */
//    public static User createEntity(EntityManager em) {
//        User user = new User();
//        user.setLogin(DEFAULT_LOGIN + RandomStringUtils.randomAlphabetic(5));
//        user.setPassword(RandomStringUtils.random(60));
//        user.setActivated(true);
//        user.setEmail(RandomStringUtils.randomAlphabetic(5) + DEFAULT_EMAIL);
//        user.setFirstName(DEFAULT_FIRSTNAME);
//        user.setLastName(DEFAULT_LASTNAME);
//        user.setImageUrl(DEFAULT_IMAGEURL);
//        user.setLangKey(DEFAULT_LANGKEY);
//        return user;
//    }
//
//    @Before
//    public void initTest() {
//        user = createEntity(em);
//        user.setLogin(DEFAULT_LOGIN);
//        user.setEmail(DEFAULT_EMAIL);
//    }
//
//}
