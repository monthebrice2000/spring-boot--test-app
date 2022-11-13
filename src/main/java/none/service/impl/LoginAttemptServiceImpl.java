package none.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptServiceImpl {

    public static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 5;
    public static final int ATTEMPT_INCREMENT = 1;
    private LoadingCache<String, Integer> loadingAttemptCache;

    public LoginAttemptServiceImpl() {
        this.loadingAttemptCache =
            CacheBuilder
                .newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(
                    new CacheLoader<String, Integer>() {
                        @Override
                        public Integer load(String s) throws Exception {
                            return 0;
                        }
                    }
                );
    }

    public void evictUserFromLoadingAttemptCache(String username) {
        loadingAttemptCache.invalidate(username);
    }

    public void addUserToLoadingAttemptCache(String username) {
        int attempts = 0;
        try {
            attempts = ATTEMPT_INCREMENT + loadingAttemptCache.get(username);
            loadingAttemptCache.put(username, attempts);
        } catch (Exception ex) {}
    }

    public boolean hasExceededMaxAttempts(String username) throws ExecutionException {
        try {
            return loadingAttemptCache.get(username) >= MAXIMUM_NUMBER_OF_ATTEMPTS;
        } catch (ExecutionException e) {
            throw e;
        }
    }
}
