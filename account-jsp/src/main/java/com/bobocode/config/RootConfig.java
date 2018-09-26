package com.bobocode.config;

import com.bobocode.TestDataGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This class provides application root (non-web) configuration.
 * <p>
 * todo: 1. Mark this class as config
 * todo: 2. Enable component scanning for all packages in "com.bobocode" using annotation property "basePackages"
 * todo: 3. Exclude web related config and beans (ignore @{@link Controller}, ignore {@link EnableWebMvc})
 * todo: 4. Configure {@link TestDataGenerator} bean with name "dataGenerator" (don't specify name explicitly)
 */
public class RootConfig {
}
