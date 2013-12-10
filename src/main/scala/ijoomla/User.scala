package ijoomla

case class Expiry(month: String, year: String)

case class Card(brand: String, number: String, sec: String, expiry: Expiry)

case class Address(street: String, city: String, state: String, postcode: String, country: String)

case class User(firstname: String, lastname: String, email: String, username: String, password: String,
                address: Address, card: Card) {

}
