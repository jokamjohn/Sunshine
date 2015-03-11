package com.example.android.sunshine.app;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * Created by DELL on 3/11/2015.
 */
public class FullTestSuite {
    public static Test Suite(){
        return new TestSuiteBuilder(FullTestSuite.class)
                .includeAllPackagesUnderHere().build();
    }
    public FullTestSuite(){
        super();
    }
}
