package org.example.project.di

import org.koin.core.module.Module

//expecting a definition of the way to handle http request (!= depending on the platform)
expect fun platformHttpClientModule(): Module
