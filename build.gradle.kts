// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  id("com.android.application") version "8.3.0-alpha11" apply false
  id("org.jetbrains.kotlin.android") version "1.9.10" apply false
  id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}
true // Needed to make the Suppress annotation work for the plugins block