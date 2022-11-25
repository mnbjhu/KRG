package returns.util

import returns.ReturnValue
import kotlin.reflect.KType

class ArrayType<T, U: ReturnValue<T>>(val type: KType)