package com.news.criticaltechworks

import com.news.criticaltechworks.common.utils.BaseApplication
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(BaseApplication::class)
interface HiltTestApplication
