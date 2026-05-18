package di


import Security.PasswordHasher
 import data.repository.UserRepositoryImpl
import domain.repository.UserRepository
import controller.AuthController
import controller.PrizeController
import data.repository.PrizeRepositoryImpl
import domain.repository.PrizeRepository
import domain.usecases.AddFavoritePrizeUseCase
import domain.usecases.GetCurrentUserUseCase
import domain.usecases.GetPrizeUseCase
import domain.usecases.GetUserFavoritesUseCase
import domain.usecases.LoginUseCase
import domain.usecases.RemoveFavoritePrizeUseCase
import kotlin.getValue

object AppContainer {
    val prizeRepository: PrizeRepository by lazy { PrizeRepositoryImpl() }
    val userRepository: UserRepository by lazy { UserRepositoryImpl(prizeRepository) }
    val loginUseCase: LoginUseCase by lazy { LoginUseCase(userRepository, PasswordHasher) }
    val getPrizeUseCase: GetPrizeUseCase by lazy { GetPrizeUseCase(prizeRepository) }
    val getCurrentUserUseCase: GetCurrentUserUseCase by lazy { domain.usecases.GetCurrentUserUseCase(userRepository) }
    val getUserFavoritesUseCase: GetUserFavoritesUseCase by lazy { domain.usecases.GetUserFavoritesUseCase(userRepository) }
    val addFavoritePrizeUseCase: AddFavoritePrizeUseCase by lazy { domain.usecases.AddFavoritePrizeUseCase(userRepository) }
    val removeFavoritePrizeUseCase: RemoveFavoritePrizeUseCase by lazy { domain.usecases.RemoveFavoritePrizeUseCase(userRepository) }
    val authController: AuthController by lazy { AuthController(loginUseCase) }
    val prizeController: PrizeController by lazy { PrizeController(getPrizeUseCase) }
    val userController: controller.UserController by lazy { controller.UserController(getCurrentUserUseCase, getUserFavoritesUseCase, addFavoritePrizeUseCase, removeFavoritePrizeUseCase) }
}

fun appModule() {
    println("DI инициализирован")
}