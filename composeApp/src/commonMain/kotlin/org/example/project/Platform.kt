package org.example.project

interface Platform {
    val name: String
}

// Define common implementations for each platform (here, we want each platform to define their name)
expect fun getPlatform(): Platform
