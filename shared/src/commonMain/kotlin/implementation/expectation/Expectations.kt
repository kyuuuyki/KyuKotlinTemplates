package implementation.expectation

expect class WeakReference<T : Any>(referred: T) {
    fun clear()

    fun get(): T?
}

expect val appDependencyManager: AppDependencyManager
