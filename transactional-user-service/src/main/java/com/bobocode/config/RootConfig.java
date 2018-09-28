package com.bobocode.config;

import org.springframework.transaction.PlatformTransactionManager;

/**
 * This class provides root application configuration. It scans for all available beans and enables declarative
 * transaction management.
 * <p>
 * todo: 1. Mark this class as config
 * todo: 2. Enable component scanning for package "com.bobocode"
 * todo: 3. Configure JPA {@link PlatformTransactionManager} with bean name "transactionManager"
 * todo: 4. Enable declarative transaction management
 */
public class RootConfig {

}
