package com.sj.partybooking.hazalcast.store;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.hazelcast.core.HazelcastInstance;


import com.hazelcast.map.MapLoaderLifecycleSupport;
import com.hazelcast.map.MapStore;
import com.sj.partybooking.domain.UserModel;
import com.sj.partybooking.repository.UserAddressDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HazalcastUserCacheStore implements MapStore<String, UserModel>, MapLoaderLifecycleSupport {

    private final Logger logger = LoggerFactory.getLogger(HazalcastUserCacheStore.class);

    @Autowired
    @Qualifier("userAddressDao")
    private UserAddressDao userAddressDao;

    @Override
    public void init(HazelcastInstance instance, Properties properties, String mapName) {
        logger.info("Initializing {} cache", mapName);

    }

    @Override
    public void destroy() {
        // Nothing to do.
    }

    @Override
    public UserModel load(final String key) {
        logger.info("load");
        return userAddressDao.findByEmail(key);
    }

    @Override
    public Map<String, UserModel> loadAll(final Collection<String> keys) {
        logger.info("loadAll");
        return null;
    }

    @Override
    public Set<String> loadAllKeys() {
        logger.info("Loading all keys from cache");

        logger.info("loadAllKeys");
        return null;
    }

    @Override
    @Transactional
    public void store(final String key, final UserModel value) {
        delete(key);
        value.setEmail(key);
        userAddressDao.saveOrUpdateUser(value);
        logger.info("store");
    }

    @Override
    public void storeAll(final Map<String, UserModel> map) {
        for (Map.Entry<String, UserModel> entry : map.entrySet()) {
            this.store(entry.getKey(), entry.getValue());
        }
        logger.info("storeAll");
    }

    @Override
    public void delete(final String key) {
        logger.info("Deleting {} from cache", key);

    }

    @Override
    public void deleteAll(final Collection<String> keys) {
        for (String key : keys) {
            this.delete(key);
        }
    }

}
