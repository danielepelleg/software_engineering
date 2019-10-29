# Winehouse

@startuml component
title Use Case Diagram

left to right direction

actor Admin
actor Client
actor Person

Person -down-|> (Admin) : <<inheritance>>
Person -right-|> (Client) : <<inheritance>>

rectangle  Winehouse_Database {
    Client -- (Registration)
    Client -down- (Login)
    Admin -- (Refill Bottles)
    Admin -- (Ship Order)
    (Login) <.. (Search Wine) : <<extends>>
    (Search Wine) <.left. (Buy Wine) : <<extends>>
    (Search Wine) <.right. (Ask Notification) : <<extends>>
}
@enduml

Maven Project

### Contributors

[Daniele Pellegrini](https://github.com/danielepelleg) - 285240

[Riccardo Fava](https://github.com/BeleRicks11) - 287516
