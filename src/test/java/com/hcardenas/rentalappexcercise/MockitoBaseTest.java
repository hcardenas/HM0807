package com.hcardenas.rentalappexcercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.util.ReflectionUtils.getField;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public abstract class MockitoBaseTest {

    @AfterEach
    void tearDown() {

        final Object[] mocks = getMocks(this);
        if (mocks.length > 0) {
            verifyNoMoreInteractions(mocks);
        }
    }

    protected Object[] getMocks(Object testClass) {
        return Arrays.stream(testClass.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Mock.class))
                .peek(field -> field.setAccessible(true))
                .map(field -> getField(field, testClass))
                .toArray(Object[]::new);
    }

}
