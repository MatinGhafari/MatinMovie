package ir.matin.matinfilm.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Timeout

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PingInterval