package com.example.somoni.extensions.const

const val APP_SETTINGS = "app_settings"
const val CURRENCY_TYPE = "currency_type"
const val SELECT_OPTION = "select_option"
const val CHANGE_MODE = "change_mode"
const val CurrencyINFO = "currencyinfo"
const val NAME_COLOR_ONE = "name_color_one"
const val NAME_COLOR_SECOND = "name_color_second"
const val CURRENCY_USD = "currency_usd"
const val CURRENCY_EUR = "currency_euro"
const val CURRENCY_RUS = "currency_rus"
const val NAME_ICON = "name_icon"
const val NAME_POSITION = "name_position"
const val NAME_CURRENCY_VALUE = "name_currency_value"
var CurrencyValue: Int = 1
const val DEFAULT_NUMBER = 0
const val GET_CURRENCY_DATA_ONE = 1
const val GET_CURRENCY_DATA_ZERO = 0
const val GET_CURRENCY_DATA_TWO = 2
const val CURRENCY_SEND_FOR_DIALOG = "currency_send_for_dialog"
const val BASE_URL = "https://transfer.humo.tj/currency-app/v1/"
const val SAVE = "Сохранить"


enum class Currency(val currencyId: Int) {
    RUB(0),
    USD(1),
    EUR(2)
}
enum class ViewType(val viewTypeId: Int) {
    SIMPLE(0),
    ADVANCE(1)
}