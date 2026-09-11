# Problem Statement

## Problem Statement

Small retail or warehouse operations often need a lightweight way to track product stock levels and process customer orders without the overhead of a full database system or graphical interface. Manual tracking (spreadsheets, paper logs) is error-prone and makes it easy to oversell stock or lose track of order history.

This project addresses that gap with a command-line Java application that manages a product inventory, validates and processes orders against real-time stock levels, and keeps a persistent, timestamped record of all transactions — all without requiring a database server or GUI framework.

## Scope of the Project

The project is scoped as a single-user, single-machine console application. It covers:

- Adding and viewing products in an inventory
- Searching/filtering products by category
- Placing orders against stock, with full validation
- Maintaining an in-memory and on-disk record of order history
- Reporting low-stock products against a configurable threshold
- Persisting inventory data between application runs using file I/O

It does **not** cover: multi-user concurrent access, a networked/client-server model, a graphical or web interface, or integration with a real payment or shipping system — these are noted as potential future enhancements in the project report.

## Target Users

- Small shop owners or warehouse staff who need a simple, no-setup way to track stock and orders.
- Students and instructors evaluating core Java concepts (Collections, exceptions, file I/O, Date/Time API) applied to a realistic problem.

## High-Level Features

- **Add Product** — register a new product with ID, name, category, price, and starting quantity.
- **View All Products / View by Category** — browse the current inventory, optionally filtered.
- **Place Order** — order a quantity of a product; stock is validated and deducted automatically.
- **View Order History** — see all orders placed during the session, most recent first.
- **View Low Stock Products** — list products below a threshold the user specifies at runtime.
- **Save & Exit** — persist the current inventory to disk (CSV) so it is available on the next run.