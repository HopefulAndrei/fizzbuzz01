package com.solved.fizzbuzz

class FizzBuzzUtil {

    companion object {

        const val MAX_LIMIT: Int = 9999

        //normally we should persist these values in a local database
        private val int1FrequencyMap = HashMap<Int, Int>()
        private val int2FrequencyMap = HashMap<Int, Int>()
        private val limitFrequencyMap = HashMap<Int, Int>()

        @JvmStatic
        fun putInt1(int1: Int): Int {
            val value: Int = int1FrequencyMap[int1] ?: 0
            int1FrequencyMap[int1] = value + 1
            return value
        }

        @JvmStatic
        fun getInt1(int1: Int): Int {
            return int1FrequencyMap[int1] ?: 0
        }

        @JvmStatic
        fun putInt2(int2: Int): Int {
            val value: Int = int2FrequencyMap[int2] ?: 0
            int2FrequencyMap[int2] = value + 1
            return value
        }

        @JvmStatic
        fun getInt2(int2: Int): Int {
            return int2FrequencyMap[int2] ?: 0
        }

        @JvmStatic
        fun putLimit(limit: Int): Int {
            val value: Int = limitFrequencyMap[limit] ?: 0
            limitFrequencyMap[limit] = value + 1
            return value
        }

        @JvmStatic
        fun getLimit(limit: Int): Int {
            return limitFrequencyMap[limit] ?: 0
        }


        @JvmStatic
        fun checkArguments(int1Str: String, int2Str: String, limitStr: String, str1: String, str2: String) : Int? {
            val int1: Int
            try {
                int1 = Integer.parseInt(int1Str)
            } catch (ex: NumberFormatException) {
                return 0
            }
            if (int1 <= 0){
                return 1
            }
            val int2: Int
            try {
                int2 = Integer.parseInt(int2Str)
             } catch (ex: NumberFormatException) {
                return 2
            }
            if (int2 <= 0){
                return 3
            }
            val limit: Int
            try {
                limit = Integer.parseInt(limitStr)
            } catch (ex: NumberFormatException) {
                return 4
            }

            if (limit <= int1 || limit <= int2) {
                return 5
            }
            if (limit > MAX_LIMIT) {
                return 6
            }
            if (str1.trim().isEmpty()) {
                return 7
            }
            if (str2.trim().isEmpty()) {
                return 8
            }
            return null
        }

        @JvmStatic
        fun FizzBuzz(int1: Int, int2: Int, limit: Int, str1: String, str2: String) : String {
            val min: Int = Math.min(int1, int2)
            val max: Int = Math.max(int1, int2)
            return StringBuilder().let {
                for (i in 1..limit) {
                    if (i%max == 0) {
                        if (i%min == 0) {
                            it.append(str1)
                        }
                        it.append(str2).append(",")
                    } else if (i%min == 0) {
                        it.append(str1).append(",")
                    } else {
                        it.append(i).append(",")
                    }
                }
                it.toString()
            }

        }
    }
}