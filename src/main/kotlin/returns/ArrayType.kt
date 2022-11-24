package returns

import kotlin.reflect.KType

class ArrayType<T, U: ReturnValue<T>>(val type: KType)