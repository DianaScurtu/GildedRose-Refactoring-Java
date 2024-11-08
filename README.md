# Gilded Rose Initial Setup in Java

The **Gilded Rose Refactoring Kata** is a coding exercise to help you practice refactoring skills and working with legacy code. In this kata, you are given existing code that simulates an inventory system for a fictional shop named "Gilded Rose." Your goal is to enhance the code’s quality without altering its existing functionality.

## Gilded Rose Requirements Overview

Welcome to the Gilded Rose team! Our shop, managed by a friendly innkeeper named Allison, is a quaint inn in a prime city location. We specialize in buying and selling high-quality goods. However, all items in our inventory lose `Quality` as they approach their `SellIn` date.

Our inventory system, created by a developer named Leeroy (now off on new adventures), automatically updates item properties daily. Your task is to add support for a new type of item while ensuring the system remains functional. Here’s a quick introduction to our system:

- Every `item` has a `SellIn` value, indicating the days left to sell it.
- Each `item` also has a `Quality` value representing its worth.
- At the end of each day, our system reduces both values for each item.

Simple enough? Well, here’s where it gets interesting:

- After the sell-by date passes, `Quality` degrades twice as quickly.
- The `Quality` of an item never goes below 0.
- **"Aged Brie"** increases in `Quality` over time.
- The `Quality` of any item is capped at 50 (with specific exceptions).
- **"Sulfuras"**, a legendary item, never has to be sold and never decreases in `Quality`.
- **"Backstage passes"** behave like "Aged Brie," increasing in `Quality` as `SellIn` approaches. However:
    - `Quality` increases by 2 when `SellIn` is 10 days or less.
    - `Quality` increases by 3 when `SellIn` is 5 days or less.
    - After the event, `Quality` drops to 0.

Recently, we also began selling **"Conjured"** items, which require system updates:

- **"Conjured"** items degrade in `Quality` twice as fast as regular items.

Feel free to make any changes to the `UpdateQuality` method and add new code if everything still functions correctly. However, do not modify the `Item` class or the `Items` property, as they belong to a goblin in the corner who insists on code ownership and will not allow changes (though making `UpdateQuality` or `Items` static is acceptable—we’ll back you up).

For clarity, the `Quality` of an item cannot exceed 50, except for **"Sulfuras"**, which has a fixed `Quality` of 80.

### Problem Summary

The Gilded Rose shop's inventory is managed by the `GildedRose` class. Each item has `name`, `quality`, and `sellIn` properties. Specific rules govern how `quality` and `sellIn` values adjust daily:
- Normally, `quality` decreases over time.
- After `sellIn` reaches 0, `quality` degrades twice as fast.
- `quality` cannot go negative and should not exceed 50 (except for special cases).
- Certain items, like "Aged Brie" and "Backstage passes," have unique quality-adjustment rules.

## Instructions

1. **Create a New Branch**: Work on your refactored version in a separate branch.
2. **Analyze Existing Code**: Review the functionality of each rule in the `GildedRose` class.
3. **Add Unit Tests**: If tests aren’t already in place, create unit tests to verify that all behaviors remain consistent.
4. **Refactor the Code**: Improve the structure by applying principles like the *Single Responsibility Principle* (SRP), extracting methods or classes, and removing duplication.
5. **Run Tests Regularly**: Run tests after each change to verify functionality remains unchanged.

### Refactoring Suggestions
- **Extract Methods and Classes**: Reduce complexity in the `updateQuality` method by breaking it into smaller, more specific methods or classes.
- **Implement Strategy Patterns**: Create separate classes for each item type, with each class handling unique behavior, following the Strategy Pattern.
- **Follow Design Principles**: Adhere to the *Open/Closed Principle* (OCP) to allow new item types to be added without modifying existing code.

-----

## Run the TextTest Fixture from Command-Line

```bash
./gradlew -q text
```

### Specify Number of Days

For e.g. 10 days:

```bash
./gradlew -q text --args 10
```

You should make sure the gradle commands shown above work when you execute them in a terminal before trying to use TextTest (see below).


## Run the TextTest approval test that comes with this project

There are instructions in the [TextTest Readme](../texttests/README.md) for setting up TextTest. What's unusual for the Java version is there are two executables listed in [config.gr](../texttests/config.gr) for Java. The first uses Gradle wrapped in a python script. Uncomment these lines to use it:

    executable:${TEXTTEST_HOME}/Java/texttest_rig.py
    interpreter:python

The other relies on your CLASSPATH being set correctly in [environment.gr](../texttests/environment.gr). Uncomment these lines to use it instead:

    executable:com.gildedrose.TexttestFixture
    interpreter:java
