package com.melbourne.webster.nit3213.repo

import com.melbourne.webster.nit3213.network.ApiService
import com.melbourne.webster.nit3213.network.DashboardResponse
import com.melbourne.webster.nit3213.network.LoginRequest
import com.melbourne.webster.nit3213.network.LoginResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val api: ApiService) {
    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return api.login(LoginRequest(username, password))
    }

    suspend fun getDashboard(keypass: String): Response<DashboardResponse> {
        return api.getDashboard(keypass)
    }
}
