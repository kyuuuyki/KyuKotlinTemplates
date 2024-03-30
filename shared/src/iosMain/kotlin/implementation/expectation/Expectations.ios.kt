package implementation.expectation

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual typealias WeakReference<T> = kotlin.native.ref.WeakReference<T>

actual val appDependencyManager: AppDependencyManager = AppDependencyManager()
