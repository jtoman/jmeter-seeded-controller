# jmeter-seeded-controller

A Random Controller for [Apache JMeter](http://jmeter.apache.org) that allows you to set a seed for how actions
are selected.

## Building

Copy the `edu` folder into the `addons` folder found at the root of the JMeter distribution
(create it if it doesn't already exists). Then, from the JMeter root directory run:

```
ant -f addons.xml
```

This will compile and install the addon for use with JMeter.

**Note**: You will have to repeat this process for every JMeter distribution with which you want to use this addon.
Alternatively, simply copy `lib/ext/ApacheJMeter_addons.jar` file from one distribution to another.

## Usage

The RandomSeededController can be found under the `Controller` test elements. The only difference is the
`Random Seed` box found on the controller's configuration page. This text box can be filled with any numeric value,
or [function or variable](http://jmeter.apache.org/usermanual/functions.html) that evaluates to a
numeric value. Note that if the field is blank, or the variable/function expression evaluates to an empty string,
no seed is used.
