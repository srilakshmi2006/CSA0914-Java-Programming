# A4 – Serialization Design

Mark password and live Connection transient. A password should not be serialized in plaintext; a live JDBC Connection is not a durable serializable value and must be recreated after loading. If a serialized field is needed, store a safe credential reference or no credential at all.

Serializing Hostel traverses its reachable object graph: Hostel → Room objects → Resident objects, including non-transient serializable fields. Every reachable non-transient object must be serializable or serialization fails with NotSerializableException. transient fields are restored to defaults and reinitialized separately.

Adding phoneNumber changes the class schema. With compatible serialVersionUID, older streams may deserialize with phoneNumber defaulting to null/0; application migration should supply a default. serialVersionUID identifies class-version compatibility. Java serialization is Java-specific and risky for untrusted input; CSV is simple and transparent, JSON is interoperable and schema-friendly but needs a library and explicit mapping.
