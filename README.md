The goal for this project is to create a tool which accepts a garmin fit file as an input and uses power and time data to estimate the distance of the outdoor ride. Inspired by the TrainerRoad Cycling podcast.

Features coming soon:
- More robust fit file handling to ensure loading arbitrary fit file doesn't break everything. Currently all tested TrainerRoad fit files work which was initial goal.
- Ability to upload an outdoor ride and use the road grade as an overlay for calculating distance since the estimation otherwise assumes an entirely flat parcours which is pretty boring and a bad estimate for most riders trying to compare trainer miles with outdoor miles.

//TODO latex this section properly
Math:
For a given Velocity V, there is a certain power required to maintain speed. Call this Pneeded

Pneeded can be calculated by the resistive force * the velocity

The resistive force is modelled by the sum of the force of gravity, rolling resistance, and drag

To find acceleration, subtract the Pneeded to maintain the current velocity from the actual power output at that time to get the available power (Pexcess)

Power for acceleration (Paccel) can be modelled as Pexcess / V

instantaneous acceleration (a) is thus equal to Paccel / total mass

to determine the new velocity from an acceleration
𝑣𝑡+Δ𝑡 = 𝑣𝑡+𝑎Δ𝑡

Physics reference:
https://www.gribble.org/cycling/power_v_speed.html
https://physics.stackexchange.com/questions/226854/how-can-i-model-the-acceleration-velocity-of-a-bicycle-knowing-only-the-power-ou
