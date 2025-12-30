🔤 What is <h3>?

<h3> is an HTML heading tag.

HTML has 6 heading tags:

<h1>  → Biggest heading (page title)
<h2>
<h3>
<h4>
<h5>
<h6>  → Smallest heading


So:

👉 <h3> = third-level heading

🧠 Why developers use <h3>

Developers use <h3> when they want to display:

Section titles

Important messages

Alerts / warnings

Error messages (like SauceDemo)

Example:

<h3>Epic sadface: Password is required</h3>


It’s:

Bigger than normal text

More noticeable

Easy to style

🔍 Why QA / Automation cares about <h3>

Because:

It clearly contains visible text

It’s usually unique on the page

Easy to assert presence or text

In your case:

<h3 data-test="error">
  Epic sadface: Password is required
</h3>


So we target:

Tag: h3

Attribute: data-test="error"

🎯 How <h3> helped us build locator

We combined:

Tag name → h3

Stable attribute → data-test="error"

Final locator:

By.cssSelector("h3[data-test='error']")

🧪 Simple real-life analogy

Think of <h3> like a bold notice board heading in an office:

Important

Easy to spot

Not random text

So testers love it 😄

🎤 Interview-ready answer (remember this)

If interviewer asks “What is h3?”, say:

“h3 is an HTML heading tag used to display section-level headings or important messages on a page.
In automation, it’s often a good candidate for assertions because it contains visible and meaningful text.”

🔹 <div> vs <span> vs <h3> (Very Important)
1️⃣ <div> — Block container (box)

What it is:

A container to group elements

Takes full width

Starts on a new line

Has no meaning by itself

Example:

<div class="error-message-container">
   <h3>Error message</h3>
</div>


In Automation:

❌ Usually NOT ideal for assertions

✅ Useful as a parent to locate child elements

Interview line:

“div is a block-level container mainly used for layout and grouping elements.”

2️⃣ <span> — Inline container (small text wrapper)

What it is:

Inline element

Takes only required width

Used for small text or icons

No layout structure

Example:

<span class="price">$29</span>


In Automation:

❌ Often too generic

❌ Text may change

✅ Sometimes used for small labels

Interview line:

“span is an inline element used to wrap small portions of text without breaking layout.”

3️⃣ <h3> — Heading (meaningful text) ⭐⭐

What it is:

A heading tag

Semantically meaningful

Visually prominent

Used for titles, alerts, errors

Example (SauceDemo):

<h3 data-test="error">
  Epic sadface: Password is required
</h3>


In Automation:

✅ EXCELLENT for assertions

✅ Contains visible text

✅ Often unique

✅ Stable

Interview line:

“Heading tags like h3 are good candidates for validation because they represent visible and meaningful conten