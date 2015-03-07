package com.mycompany.myapp.modules;

import com.mycompany.myapp.monitoring.CrashReporter;
import com.mycompany.myapp.monitoring.LoggingOnlyCrashReporter;

import dagger.Module;
import dagger.Provides;
import hugo.weaving.DebugLog;

@Module
public class CrashReporterModule {
    private final CrashReporter crashReporter;

    @DebugLog
    public CrashReporterModule() {
        this.crashReporter = new LoggingOnlyCrashReporter();
    }

    @Provides
    CrashReporter crashReporter() {
        return crashReporter;
    }
}
